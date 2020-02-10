package ljh.gold.community.enums;

/**
 * @Author: jinhanlai
 * @Date: 2020/2/5 15:40
 */
public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1);

    public int getStatus() {
        return status;
    }

    private int status;

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
