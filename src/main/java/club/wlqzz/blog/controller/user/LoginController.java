package club.wlqzz.blog.controller.user;

import club.wlqzz.blog.pojo.Announcement;
import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.AnnouceService;
import club.wlqzz.blog.service.BlogService;
import club.wlqzz.blog.service.LoginService;
import club.wlqzz.blog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private LoginService loginService;
    @Autowired
    private AnnouceService annouceService;
//    @Autowired
//    private ShiroSessionListener shiroSessionListener;

    private static final transient Logger log = LoggerFactory.getLogger(LoginController.class);

/*
    @PostMapping("/loginCheck")
    public String login(String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) throws Exception {
        User user=loginService.checkLogin(username,password);
        System.out.println("user.............."+user);
        if (user!=null) {
            session.setAttribute("loginUser", user);
            return "redirect:main";
        } else {
            model.addAttribute("error", "error");
            return "login";
        }
    }*/

    @PostMapping("/loginCheck")
    public String login(String username, String password, Model model) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            User user = (User) currentUser.getPrincipal();
            user=loginService.getUserByName(username);
            Session session = currentUser.getSession();
            session.setAttribute("loginUser", user);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
                if("user".equals(user.getType())){
                    return "redirect:/main";
                }else if ("admin".equals(user.getType())){
                    return "redirect:/admin/main";
                }

            } catch (UnknownAccountException uae) {
                log.info("There is no user with username of " + token.getPrincipal());
                String msg = "用户名不存在";
                model.addAttribute("msg", msg);
                return "login";
            } catch (IncorrectCredentialsException ice) {
                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
                String msg = "密码不正确";
                model.addAttribute("msg", msg);
                return "login";
            } catch (LockedAccountException lae) {
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
                String msg = "该用户名已被锁定";
                model.addAttribute("msg", msg);
                return "login";
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
                String msg = "未知错误";
                model.addAttribute("msg", msg);
                return "login";
            }
        }
        return "login";
    }

    @GetMapping(value = {"/", "/main"})
    public String toMain(Model model) throws Exception {
        List<Blog> blogList1 = blogService.selectAll();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        for (Blog blog : blogList1) {
            zSetOperations.add("blogRank", blog.getTitle(), blog.getCount());
        }
        Set countRank = zSetOperations.reverseRange("blogRank", 0, 4);
        List<Blog> countRanks = new ArrayList<>();
        for (Object string :
                countRank) {
            countRanks.add(blogService.selectByTitle(String.valueOf(string)));
        }
        List<Blog>blogList=blogService.selectBlogLatest();
        List<Announcement>announcements=annouceService.selectAll();
        model.addAttribute("announcements", announcements);
        model.addAttribute("countRanks", countRanks);
        model.addAttribute("blogList", blogList);
        return "main";
    }

    @GetMapping("/loginOut")
    public String loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/main";
    }

   @GetMapping("/lookAnnounce")
    public String lookAnnounce(Integer id,Model model ) throws Exception {
        Announcement announcement=annouceService.selectAnnoucement(id);
        model.addAttribute("announcement",announcement);
        return "lookAnnounce";
   }
}
