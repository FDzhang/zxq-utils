package com.fd.demo.timeconsume;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fd.demo.timeconsume.TimeConsumeApplication;
import com.fd.demo.timeconsume.annotation.TimeConsume;
import com.fd.demo.timeconsume.bean.UserTags;
import com.fd.demo.timeconsume.utils.HuTreeUtil;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/28 13:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TimeConsumeApplication.class)
public class TreeTest {

    @Autowired
    HuTreeUtil huTreeUtil;

    @Test
    public void test3(){
        huTreeUtil.get();
    }

    @Test
    public void test(){
        // 构建node列表
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();

        nodeList.add(new TreeNode<>("1", "0", "系统管理", 1));
        nodeList.add(new TreeNode<>("11", "1", "用户管理", 222222));
        nodeList.add(new TreeNode<>("111", "11", "用户添加", 0));
        nodeList.add(new TreeNode<>("2", "0", "店铺管理", 5));
        nodeList.add(new TreeNode<>("21", "2", "商品管理", 44));
        nodeList.add(new TreeNode<>("221", "2", "商品管理2", 2));

        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");

//        System.out.println(JSON.toJSONString(treeList));

        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("order");
        treeNodeConfig.setIdKey("rid");
        // 最大递归深度
        treeNodeConfig.setDeep(2);



        //转换器
        List<Tree<String>> treeNodes = TreeUtil.build(nodeList, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId());
                    tree.setParentId(treeNode.getParentId());
                    tree.setWeight(treeNode.getWeight());
                    tree.setName(treeNode.getName());
                    // 扩展属性 ...
                    tree.putExtra("extraField", 666);
                    tree.putExtra("other", new Object());
                });
        System.out.println(JSON.toJSONString(treeNodes));

    }


    @Test
    @TimeConsume
    public void test1(){
        String jsonStr = FileUtil.readUtf8String("d:/tags.txt");
        List<UserTags> userTags = JSONArray.parseArray(jsonStr, UserTags.class);

        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("sortNumber");
        treeNodeConfig.setIdKey("tagId");
        // 最大递归深度
        treeNodeConfig.setDeep(0);



        //转换器
        List<Tree<String>> treeNodes = TreeUtil.build(userTags, "1", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getTagId());
                    tree.setParentId(treeNode.getParentId());
                    tree.setWeight(treeNode.getSortNumber());
                    tree.setName(treeNode.getTag());
                    // 扩展属性 ...
                    tree.putExtra("des", treeNode.getTagDescription());
                });
        System.out.println(JSON.toJSONString(treeNodes));

    }



}
