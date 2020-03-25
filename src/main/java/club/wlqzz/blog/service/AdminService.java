package club.wlqzz.blog.service;


import club.wlqzz.blog.pojo.Announcement;
import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.User;

import java.util.List;

public interface AdminService {

    int selectUserCount(String type)throws Exception;

    int selectBlogCount()throws Exception;

    List<Blog> selectAllBlog() throws Exception;

    User selectUser(Integer id) throws Exception;

    void deleteUser(Integer id) throws Exception;
}
