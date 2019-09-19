package club.wlqzz.blog.service.impl;

import club.wlqzz.blog.mapper.UserMapper;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.UserService;
import club.wlqzz.blog.util.Md5Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) throws Exception {
        user.setPassword(Md5Class.stringToMd5(user.getPassword()));
        userMapper.insert(user);
    }

    @Override
    public void deleteUser(User user) throws Exception {
        userMapper.delete(user);
    }

    @Override
    public void updateUser(User user) throws Exception {
        userMapper.update(user);
    }

    @Override
    public User selectUser(Integer id) throws Exception {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> selectAll() throws Exception {
        return userMapper.selectAll();
    }

    @Override
    public Boolean checkLogin(Integer id,String email,String password) throws Exception {
        User user=null;
        if(id!=null){
           user =selectUser(id);
        }else if (email!=null){
            user=userMapper.selectByEmail(email);
        }
        password=Md5Class.stringToMd5(password);
        if (user!=null&&password.equals(user.getPassword())){
            return true;
        }
        return false;
    }
}
