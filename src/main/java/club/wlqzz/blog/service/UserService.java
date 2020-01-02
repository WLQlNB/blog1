package club.wlqzz.blog.service;

import club.wlqzz.blog.pojo.User;

import java.util.List;

public interface UserService {
    void addUser(User user) throws Exception;

    void deleteUser(User user) throws Exception;

    void updateUser(User user) throws Exception;

    User selectUser(Integer id) throws Exception;

    User selectUser(String email) throws Exception;

    User selectUser(Integer id, String email, String password) throws Exception;

    List<User> selectAll() throws Exception;

}
