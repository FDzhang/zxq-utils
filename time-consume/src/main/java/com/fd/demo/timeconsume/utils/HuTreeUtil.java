package com.fd.demo.timeconsume.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fd.demo.timeconsume.annotation.TimeConsume;
import com.fd.demo.timeconsume.bean.UserTags;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/28 15:20
 */
@Component
public class HuTreeUtil {

    @TimeConsume
    public void get(){
        String jsonStr = FileUtil.readUtf8String("d:/tags.txt");
        List<UserTags> userTags = JSONArray.parseArray(jsonStr, UserTags.class);

        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("sortNumber");
        treeNodeConfig.setIdKey("tagId");
        // 最大递归深度
        treeNodeConfig.setDeep(3);

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
