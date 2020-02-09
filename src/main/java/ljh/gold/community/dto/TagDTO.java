package ljh.gold.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: jinhanlai
 * @Date: 2020/2/9 11:52
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> Tags;
}
