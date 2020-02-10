package ljh.gold.community.dto;

import ljh.gold.community.model.User;
import lombok.Data;

/**
 * @Author: jinhanlai
 * @Date: 2020/2/10 11:32
 */
@Data
public class NotificationDTO {
    private Long id;//当前通知的id
    private Long gmt_create;
    private Integer status;
    private Long notifier;
    private String notifier_name;
    private String outer_title;
    private Long outerId;

    private Integer type;
    private String typeName;


}
