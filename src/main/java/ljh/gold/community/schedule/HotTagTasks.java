package ljh.gold.community.schedule;

import ljh.gold.community.cache.HotTagCache;
import ljh.gold.community.mapper.QuestionMapper;
import ljh.gold.community.model.Question;
import ljh.gold.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: jinhanlai
 * @Date: 2020/3/18 18:51
 */
@Component
@Slf4j
public class HotTagTasks {
    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate =  1000 * 60 * 60 * 3)
//    @Scheduled(cron = "0 0 1 * * * ")
    public void hotTagSchedule() {
        int offset=0,limit=20;
        log.info("hotTagSchedule start{}", LocalDateTime.now());
        List<Question> list=new ArrayList<>();
        HashMap<String,Integer> priorities=new HashMap<>(16);
        while (offset==0||list.size()==limit){
            list=questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),new RowBounds(offset,limit));
            for (Question question : list) {
                String[] tags= StringUtils.split(question.getTag(),";");
                for (String tag : tags) {
                    Integer priority=priorities.get(tag);
                    if(priority!=null){
                        priorities.put(tag,priority+5+question.getComment_count());
                    }
                    else{
                        priorities.put(tag,5+question.getComment_count());
                    }
                }
            }
            offset+=limit;
        }
        hotTagCache.updateTags(priorities);
        log.info("hotTagSchedule stop{}", LocalDateTime.now());
    }
}
