package club.wlqzz.blog.service;

import club.wlqzz.blog.pojo.User;

public interface LoginService {

    User getUserByName(String getMapByName) throws Exception;

    User getMapByName(String userName) throws Exception;

    User checkLogin(String loginId, String password) throws Exception;

    User regular(String string) throws Exception;
}
