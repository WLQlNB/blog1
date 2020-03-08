package club.wlqzz.blog.service.impl;

import club.wlqzz.blog.mapper.UserMapper;
import club.wlqzz.blog.pojo.Permission;
import club.wlqzz.blog.pojo.Role;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.LoginService;
import club.wlqzz.blog.service.UserService;
import club.wlqzz.blog.util.Md5Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Override
    public User getUserByName(String getMapByName) throws Exception {
        //模拟数据库查询，正常情况此处是从数据库或者缓存查询。
        return getMapByName(getMapByName);
    }

    /**
     * 模拟数据库查询
     *
     * @param userName
     * @return
     */
    public User getMapByName(String userName) throws Exception {
        //共添加两个用户，两个用户都是admin一个角色，
        //wsl有query和add权限，zhangsan只有一个query权限
    /*    Permissions permissions1 = new Permissions("1","query");
        Permissions permissions2 = new Permissions("2","add");
        Set<Permissions> permissionsSet = new HashSet<>();
        permissionsSet.add(permissions1);
        permissionsSet.add(permissions2);
        Role role = new Role("1","admin",permissionsSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = new User("1","wsl","123456",roleSet);
        Map<String ,User> map = new HashMap<>();
        map.put(user.getName(), user);

        Permissions permissions3 = new Permissions("3","query");
        Set<Permissions> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions3);
        Role role1 = new Role("2","user",permissionsSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User("2","zhangsan","123456",roleSet1);
        map.put(user1.getName(), user1);
        return map.get(userName);*/
        return  regular(userName);
    }


    @Override
    public User checkLogin(String loginId, String password) throws Exception {
    //    password = Md5Class.stringToMd5(password);
        User user = regular(loginId);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User regular(String string) throws Exception {
        String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String zh = "^\\d{8,}$";
        if (string.matches(em)) {
            User user = userService.selectUser(string);
            return user;

        } else if (string.matches(zh)) {
            User user = userService.selectUser(Integer.parseInt(string));
            return user;
        }
        return null;
    }


}