package club.wlqzz.blog.service.impl;

import club.wlqzz.blog.mapper.UserMapper;
import club.wlqzz.blog.pojo.Permission;
import club.wlqzz.blog.pojo.Role;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.UserService;
import club.wlqzz.blog.util.Md5Class;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) throws Exception {
       /* user.setPassword(Md5Class.stringToMd5(user.getPassword()));*/
        userMapper.insert(user);
    }

    @Override
    public void deleteUser(User user) throws Exception {
        userMapper.delete(user);
    }

    @Override
    public void updateUser(User user) throws Exception {
        System.out.println("uupp"+user);
        userMapper.update(user);
    }

    @Override
    public User selectUser(Integer id) throws Exception {
        return userMapper.selectById(id);
    }

    @Override
    public User selectUser(String email) throws Exception {
        return userMapper.selectByEmail(email);
    }


    @Override
    public List<User> selectAll() throws Exception {
        return userMapper.selectAll();
    }

    @Override
    public void setRole(User user) throws Exception {

    }

    @Override
    public Set<Role> getAllRole(Integer id) throws Exception {
        return null;
    }

    @Override
    public void setPermission(User user) throws Exception {

    }

    @Override
    public Set<Permission> getAllPermission(Integer id) throws Exception {
        return null;
    }

}
