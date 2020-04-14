package club.wlqzz.blog.controller.admin;

import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.AdminService;
import club.wlqzz.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class UserManageController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/getUser")
    public String getUser(Model model, HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) throws Exception {
        try{  //把sessionId记录在浏览器
            Cookie c = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId(), "utf-8"));
            c.setPath("/");
            //先设置cookie有效期为2天，不用担心，session不会保存2天
            c.setMaxAge( 48*60 * 60);
            response.addCookie(c);
        }catch (Exception e){
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        Object count=session.getServletContext().getAttribute("count");
        int userSum=adminService.selectUserCount("user");
        int adminSum=adminService.selectUserCount("admin");
        PageHelper.startPage(pageNum, 5);
        List<User>users=userService.selectAll();
        for (User user:
            users ) {
            if("admin".equals(user.getType())){
                user.setType("管理员");
            }else if("user".equals(user.getType())){
                user.setType("用户");
            }
        }
        PageInfo<User> pageInfo = new PageInfo<>(users);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("users",users);
        model.addAttribute("userSum",userSum);
        model.addAttribute("adminSum",adminSum);
        model.addAttribute("count",count);
        return "admin/userManage";
    }

   @PostMapping("/admin/getUserInfo")
    public String getUser(@RequestParam("id") Integer id,Model model) throws Exception {
       User user= userService.selectUser(id);
       if("admin".equals(user.getType())){
           user.setType("管理员");
       }else if("user".equals(user.getType())){
           user.setType("用户");
       }
       model.addAttribute("user",user);
       return "admin/users";
   }

   @GetMapping("/admin/deleteUser")
    public String deleteUser(Integer id) throws Exception {
       adminService.deleteUser(id);
        return "redirect:/admin/getUser";
   }

   @GetMapping("/admin/searchUser")
    public String searchUser(Integer id,Model model) throws Exception {
        User user=userService.selectUser(id);
        model.addAttribute("user",user);
        return "admin/userInfo";
   }

}
