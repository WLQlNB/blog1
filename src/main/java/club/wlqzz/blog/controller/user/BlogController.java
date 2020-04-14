package club.wlqzz.blog.controller.user;

import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.Comments;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.BlogService;
import club.wlqzz.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public String toBlog(Model model,@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) throws Exception {
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogList = blogService.selectAll();
        Collections.reverse(blogList);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("blogList", blogList);
        return "technology";
    }


    @PostMapping("/blog")
    public String toTech(Blog blog, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("loginUser");
        blog.setUserId(user.getId());
        blogService.addBlog(blog);
        return "redirect:/technology";
    }

    @GetMapping("/lookBlog")
    public String toLook(Integer id,String title, Model model) throws Exception {
        Blog blog=null;
        if(id!=null){
            blog = blogService.selectBlog(id);
        }else {
            blog=blogService.selectByTitle(title);
        }
        blog.setCount(blog.getCount() + 1);
        blogService.updateBlog(blog);
        User user = userService.selectUser(blog.getUserId());
        List<Comments> comments=blogService.selectComments(blog.getId());
        model.addAttribute("comments",comments);
        model.addAttribute("blog", blog);
        model.addAttribute("Author", user.getName());
        return "/lookBlog";
    }

    @PostMapping("/comment/{id}")
    public String Comment(@PathVariable("id") Integer id,Comments comments,HttpSession session,Model model) throws Exception {
       User user = (User) session.getAttribute("loginUser");
        comments.setBlogId(id);
        comments.setUserId(user.getId());
        blogService.addComments(comments);
        return "redirect:/main";
    }

    @GetMapping("/searchBlog")
    public String searchBlog(@RequestParam("title") String title,Model model,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) throws Exception {
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogList=blogService.selectBlogs(title);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo", pageInfo);
        return "searchResult";
    }
}
