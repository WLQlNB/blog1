package club.wlqzz.blog.service;


import club.wlqzz.blog.pojo.Blog;

import java.util.List;

public interface AdminService {

    int selectUserCount(String type)throws Exception;

    int selectBlogCount()throws Exception;

    List<Blog> selectAllBlog();

}
