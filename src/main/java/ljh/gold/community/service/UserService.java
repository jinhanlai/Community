package ljh.gold.community.service;

import ljh.gold.community.mapper.UserMapper;
import ljh.gold.community.model.User;
import ljh.gold.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userexample = new UserExample();
        userexample.createCriteria()
                .andAccount_idEqualTo(user.getAccount_id());
        List<User> users = userMapper.selectByExample(userexample);
        if (users.size()!=0) {
            User dbuser = users.get(0);
            User updateuser = new User();
            updateuser.setGmt_modified(System.currentTimeMillis());
            updateuser.setAvatar_url(user.getAvatar_url());
            updateuser.setName(user.getName());
            updateuser.setBio(user.getBio());
            updateuser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andAccount_idEqualTo(dbuser.getAccount_id());
            userMapper.updateByExampleSelective(updateuser, example);
        } else {
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
        }
    }
}
