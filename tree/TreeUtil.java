
import com.alibaba.fastjson.JSON;
import annotation.TimeConsume;
import bean.UserTags;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/19 17:46
 */
@Component
@Slf4j
public class TreeUtil {
    private List<UserTags> topList;
    private List<UserTags> subList;
    private Set<String> parentIdSet;

    @TimeConsume
    public List<UserTags> getChildTree(List<UserTags> list, Set<String> topIdSet) {
        if (list != null && list.size() > 0) {
            topList = new ArrayList<>();
            subList = new ArrayList<>();
            parentIdSet = new HashSet<>();

            // 区分最顶级的list和subList
            getTopAndSubList(list, topIdSet);

            return getTree();
        }
        return list;
    }

    /**
     * 区分顶级节点和子节点
     */
    private void getTopAndSubList(List<UserTags> list, Set<String> topIdSet) {
        for (UserTags userTags : list) {
            if (StringUtils.isNotEmpty(userTags.getParentId())) {
                parentIdSet.add(userTags.getParentId());
            }
        }
        for (UserTags userTags : list) {
            //获取最顶级的list
            if (topIdSet.contains(userTags.getTagId())) {
                topList.add(userTags);
            } else {
                subList.add(userTags);
            }
        }
        topList.sort((o1, o2) -> -(o2.getSortNumber()-o1.getSortNumber()));
    }

    /**
     * 创建树形结构
     */
    private List<UserTags> getTree() {
        List<UserTags> resList = new ArrayList<>();
        if (topList.size() > 0 && subList.size() > 0) {
            for (UserTags topTags : topList) {
                List<UserTags> subOneList = new ArrayList<>();

                for (UserTags sub : subList) {
                    // 根据传入的某个父节点Id, 遍历改父节点的所有子节点
                    if (StringUtils.equals(sub.getParentId(), topTags.getTagId())) {
                        recursionFn(subList, sub);
                        subOneList.add(sub);
                    }
                }
                if (subOneList.size()>0){
                    subOneList.sort((o1, o2) -> -(o2.getSortNumber()-o1.getSortNumber()));
                    topTags.setChildren(subOneList);
                }
                resList.add(topTags);
            }
        }
        return resList;
    }

    /**
     * 递归 将子节点的list归并到父节点中
     */
    private void recursionFn(List<UserTags> list, UserTags userTags) {
        List<UserTags> childList = getChildList(list, userTags);
        if (childList.size() > 0) {
            userTags.setChildren(childList);
        }

        for (UserTags child : childList) {
            // 判断是否有子节点
            if (hasChild(child)) {
                recursionFn(list, child);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<UserTags> getChildList(List<UserTags> list, UserTags userTags) {
        List<UserTags> childList = new ArrayList<>();
        for (UserTags tags : list) {
            if (StringUtils.equals(tags.getParentId(), userTags.getTagId())) {
                childList.add(tags);
            }
        }
        childList.sort((o1, o2) -> (o2.getSortNumber()-o1.getSortNumber()));
        return childList;
    }

    /**
     * 判断当前节点是否有子节点
     */
    private boolean hasChild(UserTags tags) {
        return parentIdSet.contains(tags.getTagId());
    }


    public static void main(String[] args) {
        List<UserTags> userTags = new ArrayList<>();

        UserTags tags6 = new UserTags();
        tags6.setTagId("1");
        tags6.setParentId("");

        UserTags tags = new UserTags();
        tags.setTagId("1-1");
        tags.setParentId("1");

        UserTags tags5 = new UserTags();
        tags5.setTagId("1-2");
        tags5.setParentId("1");

        UserTags tags2 = new UserTags();
        tags2.setTagId("1-1-1");
        tags2.setParentId("1-1");
        UserTags tags3 = new UserTags();
        tags3.setTagId("1-1-2");
        tags3.setParentId("1-1");

        UserTags tags4 = new UserTags();
        tags4.setTagId("1-1-1-1");
        tags4.setParentId("1-1-1");

        UserTags tags7 = new UserTags();
        tags7.setTagId("2");
        tags7.setParentId("");
        UserTags tags8 = new UserTags();
        tags8.setTagId("2-1");
        tags8.setParentId("2");


        userTags.add(tags);
        userTags.add(tags2);
        userTags.add(tags3);
        userTags.add(tags4);
        userTags.add(tags5);
        userTags.add(tags6);
        userTags.add(tags7);
        userTags.add(tags8);

        Set<String> set = new HashSet<>();
        set.add("1");
//        set.add("2");

        if (userTags != null && userTags.size() > 0) {
            TreeUtil treeUtil = new TreeUtil();
            List<UserTags> tree = treeUtil.getChildTree(userTags, set);
            System.out.println(JSON.toJSONString(tree));
        }
    }
}
