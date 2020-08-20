
import com.alibaba.fastjson.JSON;
import bean.UserTags;

import java.util.*;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/19 17:46
 */

public class TreeUtil {
    private boolean isEqualsParentId(String p1, String p2) {
        if (p1 != null && p2 != null) {
            return p1.equals(p2);
        }
        if (p1 == null && p2 == null) {
            return true;
        }
        if (p1 == null) {
            return "".equals(p2);
        }
        if (p2 == null) {
            return "".equals(p1);
        }
        return false;
    }

    public List<UserTags> getChildTree(List<UserTags> list, String parentId) {
        List<UserTags> resList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (UserTags userTags : list) {
                if (isEqualsParentId(userTags.getParentId(), parentId)) {
                    recursionFn(list, userTags);
                    resList.add(userTags);
                }
            }
        }
        return resList;
    }

    public List<UserTags> getChildTree(List<UserTags> list, String[] topIdArr) {
        List<UserTags> resList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            for (String topId : topIdArr) {
                for (UserTags userTags : list) {
                    if (isEqualsParentId(userTags.getParentId(), topId)) {
                        recursionFn(list, userTags);
                        resList.add(userTags);
                    }
                }
            }
        }
        return resList;
    }

    public List<UserTags> getChildTree(List<UserTags> list) {
        if (list != null && list.size() > 0) {
            List<UserTags> topList = new ArrayList<>();
            List<UserTags> subList = new ArrayList<>();
            // 区分最顶级的list和subList
            getTopAndSubList(list, topList, subList);

            List<UserTags> resList = getTree(topList, subList);
            if (resList != null) {
                return resList;
            }
        }
        return list;
    }

    private List<UserTags> getTree(List<UserTags> topList, List<UserTags> subList) {
        if (topList.size() > 0 && subList.size() > 0) {
            List<UserTags> resList = new ArrayList<>();
            for (UserTags topTags : topList) {
                List<UserTags> subOneList = new ArrayList<>();

                for (UserTags sub : subList) {
                    // 根据传入的某个父节点Id, 遍历改父节点的所有子节点
                    if (isEqualsParentId(sub.getParentId(), topTags.getTagId())) {
                        recursionFn(subList, sub);
                        subOneList.add(sub);
                    }
                }

                topTags.setChildren(subOneList);

                resList.add(topTags);
            }
            return resList;
        }
        return null;
    }

    private void getTopAndSubList(List<UserTags> list, List<UserTags> topList, List<UserTags> subList) {
        Map<String, String> idMap = new HashMap<>();
        for (UserTags userTags : list) {
            //归并所有list的id集合
            idMap.put(userTags.getTagId(), userTags.getTagId());
        }

        for (UserTags userTags : list) {
            //获取最顶级的list
            if (StringUtils.isEmpty(userTags.getParentId())) {
                topList.add(userTags);
            } else {
                String id = idMap.get(userTags.getParentId());
                if (StringUtils.isEmpty(id)) {
                    topList.add(userTags);
                } else {
                    subList.add(userTags);
                }
            }
        }
    }

    private void recursionFn(List<UserTags> list, UserTags userTags) {
        List<UserTags> childList = getChildList(list, userTags);
        if (childList.size() > 0) {
            userTags.setChildren(childList);
        }

        for (UserTags child : childList) {
            // 判断是否有子节点
            if (hasChild(list, child)) {
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
            if (isEqualsParentId(tags.getParentId(), userTags.getTagId())) {
                childList.add(tags);
            }
        }
        return childList;
    }

    private boolean hasChild(List<UserTags> list, UserTags tags) {
        return getChildList(list, tags).size() > 0;
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

        userTags.add(tags);
        userTags.add(tags2);
        userTags.add(tags3);
        userTags.add(tags4);
        userTags.add(tags5);
        userTags.add(tags6);


        if (userTags != null && userTags.size() > 0) {
            TreeUtil treeUtil = new TreeUtil();
            List<UserTags> tree = treeUtil.getChildTree(userTags);
            System.out.println(JSON.toJSONString(tree));
        }
    }
}
