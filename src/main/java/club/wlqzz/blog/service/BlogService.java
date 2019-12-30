package club.wlqzz.blog.service;

import club.wlqzz.blog.pojo.Blog;

import java.util.List;

public interface BlogService {
    void addBlog(Blog blog) throws Exception;

    void deleteBlog(Integer id) throws Exception;

    void updateBlog(Blog blog) throws Exception;

    Blog selectBlog(Integer id) throws Exception;

    List<Blog> selectAll() throws Exception;

    List<Blog> selectAllUser(Integer userId) throws Exception;

    Blog selectByTitle(String title) throws Exception;

}
