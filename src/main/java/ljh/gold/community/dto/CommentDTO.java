package ljh.gold.community.dto;

import ljh.gold.community.model.User;
import lombok.Data;

/**
 * @Author: jinhanlai
 * @Date: 2020/2/7 10:54
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parent_id;
    private Integer type;
    private Long commentator;
    private Long gmt_create;
    private Long gmt_modified;
    private Long like_count;
    private String content;
    private User user;
}