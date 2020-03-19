package ljh.gold.community.service;


import ljh.gold.community.dto.PaginationDOT;
import ljh.gold.community.dto.QuestionDTO;
import ljh.gold.community.dto.QuestionQueryDTO;
import ljh.gold.community.exception.CustomizeErrorCode;
import ljh.gold.community.exception.CustomizeException;
import ljh.gold.community.mapper.QuestionExtMapper;
import ljh.gold.community.mapper.QuestionMapper;
import ljh.gold.community.mapper.UserMapper;
import ljh.gold.community.model.Question;
import ljh.gold.community.model.QuestionExample;
import ljh.gold.community.model.User;
import ljh.gold.community.model.UserExample;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private QuestionExtMapper questionExtMapper;


    public PaginationDOT list(String search, Integer page, Integer size, String tag, String sort) {
        if (StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, ' ');
             search = String.join("|", tags).replace("+", "\\\\+");
        }

        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);
        if (StringUtils.isNotBlank(tag)) {
            tag = tag.replace("+", "").replace("*", "").replace("?", "");
            questionQueryDTO.setTag(tag);
        }
        Integer totalCount = questionExtMapper.countBySearch(questionQueryDTO);
        Integer totalPage=0;
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

        questionQueryDTO.setPage(offset);
        questionQueryDTO.setSize(size);
        List<Question> questions = questionExtMapper.selectBySearch(questionQueryDTO);

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDOT<QuestionDTO> paginationDOT = new PaginationDOT<>();
        for (Question question : questions) {
            UserExample example = new UserExample();
            example.createCriteria().andAccount_idEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(example);
            User user = users.get(0);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//快速拷贝对象name
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDOT.setData(questionDTOList);
        paginationDOT.setPagination(page, totalPage);
        return paginationDOT;
    }

    public PaginationDOT listByCreator(Long account_id, Integer page, Integer size) {
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(account_id);
        Integer totalCount = (int) questionMapper.countByExample(example);
        Integer totalPage = 0;
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

        QuestionExample example1 = new QuestionExample();
        example1.createCriteria().andCreatorEqualTo(account_id);
        example1.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example1, new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDOT<QuestionDTO> paginationDOT = new PaginationDOT<>();

        for (Question question : questions) {
            UserExample example2 = new UserExample();
            example2.createCriteria().andAccount_idEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(example2);
            User user = users.get(0);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//快速拷贝对象
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDOT.setData(questionDTOList);
        paginationDOT.setPagination(page, totalPage);
        return paginationDOT;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        UserExample example = new UserExample();
        example.createCriteria().andAccount_idEqualTo(question.getCreator());
        List<User> users = userMapper.selectByExample(example);
        User user = users.get(0);

        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);//快速拷贝对象
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            questionMapper.insertSelective(question);
        } else {
            Question updatequestion = new Question();
            updatequestion.setGmt_modified(System.currentTimeMillis());
            updatequestion.setTitle(question.getTitle());
            updatequestion.setDescription(question.getDescription());
            updatequestion.setTag(question.getTag());

            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            int update = questionMapper.updateByExampleSelective(updatequestion, questionExample);//updateByExampleSelective这个方法的参数是只包含更新的参数对象
            if (update != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void increaseView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setView_count(1);
        questionExtMapper.increaseView(question);

    }

    public List<QuestionDTO> selectRelated(QuestionDTO questionQueryDTO) {
        if (StringUtils.isBlank(questionQueryDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(questionQueryDTO.getTag(), ';');
        String regexTag = String.join("|", tags).replace("+", "\\\\+");

        Question question = new Question();
        question.setId(questionQueryDTO.getId());
        question.setTag(regexTag);

        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());

        return questionDTOS;
    }
}
