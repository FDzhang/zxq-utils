package com.fd.demo.utils.tree;

import cn.hutool.core.io.FileUtil;
import lombok.Data;

import java.util.*;

/**
 * 用于初始化录入 树形结构 的目录数据
 *
 * @author zhangxinqiang
 * @create 2021/11/29 15:36
 */
public class TreeUtil {

    @Data
    public static class Menu {
        private Integer id;

        private String name;

        private Integer parentId;

        private Integer level;

        private Integer orderNum;

        private String department;

    }

    /**
     * 根据 name 生成 level  （需要满足前提：目录名称 隐含了 层级信息 ）
     * <p>
     * 根据 id+name+level 生成 parentId (id 数据库主键，可以依据目前的最大id 来自增)
     * <p>
     * 依据 parentId+name+level 生成文件 （orderNum自动生成, department手动设置）
     */
    public static void generate(int id, String path, String department) {
        List<Menu> menus = new ArrayList<>();

        menuNameGLevel(path, menus);

        // 递增id
        menuGId(id, menus);

        // 设置父id
        menuGParentId(menus);

        // 生成文件， 可以直接复制粘贴到 DataGrip 来添加数据 （注意列的顺序）
        menuGFile(menus, department);
    }

    private static void menuGFile(List<Menu> menus, String department) {
        int i = 0;
        for (Menu menu : menus) {
            menu.setOrderNum(++i);
            menu.setDepartment(department);
        }

        List<String> lines = new ArrayList<>();
        for (Menu menu : menus) {
            String sb = menu.getParentId() + "\t" +
                    menu.getName() + "\t" +
                    menu.getLevel() + "\t" +
                    menu.getOrderNum() + "\t" +
                    menu.getDepartment();
            lines.add(sb);
        }


        if (menus.size() > 0) {
            Menu menu = menus.get(0);
            long now = Optional.ofNullable(menu.getId()).orElse(0);

            String path = "e:/" + department + now + ".txt";
            FileUtil.writeUtf8Lines(lines, path);
        }
    }

    private static void menuGParentId(List<Menu> menus) {
        // 存 level 对应的 最新id
        Map<Integer, Integer> map = new HashMap<>();
        for (Menu menu : menus) {
            // 将当前的 条目id 放入 map
            map.put(menu.getLevel(), menu.getId());

            // 取出当前的 条目 对应的parentId
            int parentId = map.getOrDefault(menu.getLevel() - 1, 0);
            menu.setParentId(parentId);
        }
    }

    // id Include
    private static void menuGId(int id, List<Menu> menus) {
        for (Menu menu : menus) {
            menu.setId(id++);
        }
    }

    private static void menuNameGLevel(String path, List<Menu> menus) {
        List<String> list = FileUtil.readUtf8Lines(path);

        for (String line : list) {
            Menu m = new Menu();
            // 目录名称 隐含了 层级信息   ！！！！（关键）
            int level = line.split("、")[0].split("\\.").length;

            m.setName(line);
            m.setLevel(level);

            menus.add(m);
        }
    }


    // 初始版本 （存）
    public static void read() {
        List<String> list = FileUtil.readUtf8Lines("e:/menu1.txt");

        List<Menu> menus = new ArrayList<>();
        for (String line : list) {
            Integer id = Integer.parseInt(line.split("\t")[0]);
            String name = line.split("\t")[1];

            Menu m = new Menu();
            int level = name.split("、")[0].split("\\.").length;

            m.setId(id);
            m.setName(name);
            m.setLevel(level);

            menus.add(m);
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (Menu menu : menus) {
            // 将当前的 条目id 放入map
            List<Integer> cur = map.getOrDefault(menu.getLevel(), new ArrayList<>());
            cur.add(menu.getId());
            map.put(menu.getLevel(), cur);

            // 取出当前的 条目 对应的parentId
            List<Integer> parents = map.getOrDefault(menu.getLevel() - 1, Arrays.asList(0));
            int parentId = parents.get(parents.size() - 1);
            menu.setParentId(parentId);
        }

        for (Menu menu : menus) {
            System.err.print(menu.getParentId() + "\t");
            System.err.print(menu.getName() + "\t");
            System.err.println(menu.getLevel());
        }
    }

    public static void main(String[] args) {
//        generate(90, "e:/menu1.txt", "department1");


        String[] d = {
                "department2",
                "department3",
                "department4",
        };

        int i = 1124;
        int space = 70;
        for (String department : d) {
            generate(i, "e:/menu1.txt", department);
            i += space;
        }

        System.err.println("success");
    }

}
