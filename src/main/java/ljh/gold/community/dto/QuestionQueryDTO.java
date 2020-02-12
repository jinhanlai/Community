package ljh.gold.community.dto;

import lombok.Data;

/**
 * @Author: jinhanlai
 * @Date: 2020/2/12 18:53
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer size;
    private Integer page;
}
