package club.wlqzz.blog.controller;

import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.BlogService;
import club.wlqzz.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;


    @GetMapping("/technology")
    public String toBlog(Model model) throws Exception {
        List<Blog> blogList=blogService.selectAll();
        Collections.reverse(blogList);
        model.addAttribute("blogList",blogList);
        return "technology";
    }


    @PostMapping("/blog")
    public String toTech(Blog blog,HttpSession session) throws Exception {
        User user= (User) session.getAttribute("loginUser");
        blog.setUserId(user.getId());
        blogService.addBlog(blog);
       return "redirect:/technology";
    }

    @GetMapping("/lookBlog/{id}")
    public String toLook(@PathVariable("id")Integer id,Model model) throws Exception {
        Blog blog=blogService.selectBlog(id);
        blog.setCount(blog.getCount()+1);

        blogService.updateBlog(blog);
        User user=userService.selectUser(blog.getUserId());
        model.addAttribute("blog",blog);
        model.addAttribute("Author",user.getName());
        return "/lookBlog";
    }

}
