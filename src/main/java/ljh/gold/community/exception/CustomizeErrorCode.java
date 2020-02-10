package ljh.gold.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "找的问题不存在，请重试！！"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论！！"),
    NOT_LOGIN(2003, "当前操作需要登录，请先登录后在操作！！"),
    SYSTEM_ERROR(2004, "服务器出错了，请稍后再试！！"),
    TYPE_PARAM_WRONG(2005, "评论类型错误，或不存在！！"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在！！"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空！！"),
    READ_NOTIFICATION_FAILED(2008,"这是别人的信息，阅读失败！！"),
    NOTIFICATION_NOT_FOUND(2009,"通知信息不存在！！"),
    ;

    private String message;
    private Integer code;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}

