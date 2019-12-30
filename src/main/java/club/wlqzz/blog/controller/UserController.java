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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String toUserPage(HttpSession session, Model model) throws Exception {
            User user = (User) session.getAttribute("loginUser");
            user=userService.selectUser(user.getId());
            model.addAttribute("user", user);
            return "user/myInfor";
    }

    @GetMapping("/article")
    public String lookMyArticle(HttpSession session, Model model) throws Exception {
        User user = (User) session.getAttribute("loginUser");
        List<Blog> aList = blogService.selectAllUser(user.getId());
        Collections.reverse(aList);
        model.addAttribute("aList", aList);
        return "user/article";
    }

    @GetMapping("/editArticle/{id}")
    public String editArticle(@PathVariable("id") Integer id, Model model) throws Exception {
        Blog blog = blogService.selectBlog(id);
        model.addAttribute("blog", blog);
        return "user/editArticle";
    }

    @PostMapping("/updateArticle")
    public String updateArticle(Blog blog) throws Exception {
        blogService.updateBlog(blog);
        return "redirect:/user/article";
    }

    @GetMapping("/deleteArticle")
    public String deleteArticle(Blog blog) throws Exception {
        blogService.deleteBlog(blog.getId());
        return "redirect:/user/article";
    }


    @PostMapping("/editUserInformation")
    public String editUserInformation(@RequestParam("userId") Integer userId,@RequestParam("userName")String userName,
                                      @RequestParam("userAge")Integer userAge,@RequestParam("userSex")Character userSex,
                                      @RequestParam("userEmail")String userEmail) throws Exception {
        User user=userService.selectUser(userId);
        user.setEmail(userEmail);
        user.setName(userName);
        user.setAge(userAge);
        user.setSex(userSex);
        userService.updateUser(user);
        return "redirect:/main";
    }

    @GetMapping("/writeBlog")
    public String towriteBlog(){
        return "user/writeBlog";
    }
}
