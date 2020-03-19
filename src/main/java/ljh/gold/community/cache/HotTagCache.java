package ljh.gold.community.cache;

import ljh.gold.community.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: jinhanlai
 * @Date: 2020/3/19 19:32
 */
@Component
@Data
public class HotTagCache {
    private List<String> hots=new ArrayList<>();
    public  void updateTags(HashMap<String, Integer> tags) {
        int max=10;
        PriorityQueue<HotTagDTO> priorityQueue=new PriorityQueue<>();

        tags.forEach((tag,priority)->{
            HotTagDTO hotTagDTO=new HotTagDTO();
            hotTagDTO.setTag(tag);
            hotTagDTO.setPriority(priority);
            if(priorityQueue.size()<=max){
                priorityQueue.add(hotTagDTO);
            }else {
                //priorityQueue队列会让元素从小到大排序，入队的对象必须实现compareTo方法
                //peek()会返回最小的元素
                HotTagDTO minHot=priorityQueue.peek();
                if(hotTagDTO.compareTo(minHot)>0){
                    priorityQueue.poll();
                    priorityQueue.offer(hotTagDTO);
                }
            }
        });
        List<String> sortedTags=new ArrayList<>();
        HotTagDTO poll=priorityQueue.poll();
        while(poll!=null){
            sortedTags.add(0,poll.getTag());
            poll=priorityQueue.poll();
        }
        hots=sortedTags;
    }
}
