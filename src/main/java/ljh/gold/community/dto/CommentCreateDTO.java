package ljh.gold.community.dto;

import lombok.Data;

/**
 * @Author: jinhanlai
 * @Date: 2020/2/5 11:48
 */
@Data
public class CommentCreateDTO {
    private Long parent_id;
    private String content;
    private Integer type;
}
