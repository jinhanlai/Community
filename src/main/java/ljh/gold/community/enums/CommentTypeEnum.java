package ljh.gold.community.enums;

/**
 * @Author: jinhanlai
 * @Date: 2020/2/5 15:40
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum:CommentTypeEnum.values()){
            if (commentTypeEnum.getType()==type){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
