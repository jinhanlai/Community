package ljh.gold.community.dto;

import lombok.Data;

/**
 * @Author: jinhanlai
 * @Date: 2020/3/19 19:34
 */
@Data
public class HotTagDTO implements Comparable {
    private String tag;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return  this.getPriority()-((HotTagDTO)o).getPriority();
    }
}
