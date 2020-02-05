package ljh.gold.community.mapper;

import ljh.gold.community.model.Question;
import ljh.gold.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 自定义的QuestionExtMapper用来做阅读数的增加，同时添加了自定义的QuestionExtMapper.xml文件
 */
public interface QuestionExtMapper {
    int increaseView(Question record);

    int increaseCommentCount(Question record);

}