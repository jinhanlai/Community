package ljh.gold.community.service;


import ljh.gold.community.dto.PaginationDOT;
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
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private QuestionMapper questionMapper;

    public PaginationDOT list(Integer page, Integer size) {

        Integer totalCount = questionMapper.count();
        Integer totalPage = 0;
        Integer offset = 0;
        if (totalCount != 0) {
            if (totalCount % size == 0) {
                totalPage = totalCount / size;
            } else {
                totalPage = totalCount / size + 1;
            }
            if (page <= 1) {
                page = 1;
            }
            if (page > totalPage) {
                page = totalPage;
            }
            offset = size * (page - 1);
        }


        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDOT paginationDOT = new PaginationDOT();
        for (Question question : questions) {
            User user = userMapper.findByAccount_id(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//快速拷贝对象
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDOT.setQuestions(questionDTOList);
        paginationDOT.setPagination(page, totalPage);
        return paginationDOT;
    }

    public PaginationDOT listByCreator(Integer account_id, Integer page, Integer size) {
        Integer totalCount = questionMapper.countByCreator(account_id);
        Integer totalPage=0;
        Integer offset = 0;
        if (totalCount != 0) {
            if (totalCount % size == 0) {
                totalPage = totalCount / size;
            } else {
                totalPage = totalCount / size + 1;
            }
            if (page < 1) {
                page = 1;
            }
            if (page > totalPage) {
                page = totalPage;
            }
            offset = size * (page - 1);
        }

        List<Question> questions = questionMapper.listByCreator(account_id, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDOT paginationDOT = new PaginationDOT();
        for (Question question : questions) {
            User user = userMapper.findByAccount_id(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//快速拷贝对象
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDOT.setQuestions(questionDTOList);
        paginationDOT.setPagination(page, totalPage);
        return paginationDOT;
    }
}
