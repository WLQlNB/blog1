package club.wlqzz.blog.controller.admin;

import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserManageController {
    @Autowired
    private UserService userService;

   @GetMapping("/getUserInfo")
    public String getUserInfo(){

       return "";
   }
}
