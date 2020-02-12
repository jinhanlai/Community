package ljh.gold.community.mapper;

import ljh.gold.community.dto.QuestionQueryDTO;
import ljh.gold.community.model.Question;

import java.util.List;

/**
 * 自定义的QuestionExtMapper用来做阅读数的增加，同时添加了自定义的QuestionExtMapper.xml文件
 */
public interface QuestionExtMapper {
    int increaseView(Question record);

    int increaseCommentCount(Question record);

    List<Question> selectRelated(Question question);

    int countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}