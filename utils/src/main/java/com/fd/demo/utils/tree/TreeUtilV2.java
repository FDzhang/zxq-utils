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
public class TreeUtilV2 {

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
    public static void generate(String path, String department) {
        List<Menu> menus = new ArrayList<>();

        menuNameGLevel(path, menus);

        // 生成文件， 可以直接复制粘贴到 DataGrip 来添加数据 （注意列的顺序）
        menuGFile(menus, department);
    }

    private static void menuGFile(List<Menu> menus, String department) {
        int i = 0;
        for (Menu menu : menus) {
            menu.setOrderNum(++i);
            menu.setDepartment(department);
        }


        // sqls
        List<String> lines = new ArrayList<>();
        // 存 level 对应的 最新name
        Map<Integer, String> map = new HashMap<>();

        for (Menu menu : menus) {
            // 将当前的 条目name 放入 map
            map.put(menu.getLevel(), menu.getName());
            // 取出当前的 条目 对应的 parentId
            String parentName = map.getOrDefault(menu.getLevel() - 1, "");


            String preParentSql = "(select isnull(id, 0) from dp.dbo.dp_review_menu_copy where name='%s' and department='%s')";
            String parentIdSql = String.format(preParentSql, parentName, department);

            String preSql = "INSERT INTO dp.dbo.dp_review_menu_copy(name, parent_id, level, order_num, department) " +
                    "VALUES ('%s', %s, '%s', '%s', '%s');";
            String sql = String.format(preSql, menu.getName(), parentIdSql, menu.getLevel(), menu.getOrderNum(), department);

            lines.add(sql);
        }
        map.clear();


        if (menus.size() > 0) {
            long now = System.currentTimeMillis() % 1000;

            String path = "e:/" + department + now + ".txt";
            FileUtil.writeUtf8Lines(lines, path);
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

    public static void main(String[] args) {
//        generate(90, "e:/menu1.txt", "department1");


        String[] d = {
                "department2",
                "department3",
                "department4",
        };


        String path = "e:/menu1.txt";
        for (String department : d) {
            generate( path, department);
        }

        System.err.println("success");
    }

}
