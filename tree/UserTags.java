
import lombok.Data;

import java.util.List;

@Data
public class UserTags {
    private String tagId;
    private String parentId;
    private String parentName;
    private String tag;
    private String tagDescription;
    private String tagType;
    private Integer tagsNumber;
    private Integer userNumber;
    private String tagParent;
    private Integer sortNumber;//排序字段
    private List<UserTags> children;
}
