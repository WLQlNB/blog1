package club.wlqzz.blog.service;

import club.wlqzz.blog.pojo.Permission;
import club.wlqzz.blog.pojo.Role;
import club.wlqzz.blog.pojo.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void addUser(User user) throws Exception;

    void deleteUser(User user) throws Exception;

    void updateUser(User user) throws Exception;

    User selectUser(Integer id) throws Exception;

    User selectUser(String email) throws Exception;

    List<User> selectAll() throws Exception;

    void setRole(User user) throws Exception;

    Set<Role> getAllRole(Integer id) throws Exception;

    void setPermission(User user) throws Exception;

    Set<Permission> getAllPermission(Integer id) throws Exception;

}
