package ljh.gold.community.service;


import ljh.gold.community.dto.QuestionDTO;
import ljh.gold.community.mapper.QuestionMapper;
import ljh.gold.community.mapper.UserMapper;
import ljh.gold.community.model.Question;
import ljh.gold.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired(required=false)
    private UserMapper userMapper;
    @Autowired(required=false)
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list() {
        List<Question>questions=questionMapper.list();
        List<QuestionDTO>questionDTOList=new ArrayList<>();
        for (Question question:questions){
            User user=userMapper.findByAccount_id(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//快速拷贝对象
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
