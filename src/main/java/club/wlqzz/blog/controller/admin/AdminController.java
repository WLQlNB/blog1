package club.wlqzz.blog.controller.admin;

import club.wlqzz.blog.pojo.Announcement;
import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.service.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AdminController {
    private static final transient Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/main")
    public String mainAdmin(){

        return "admin/mainAdmin";
    }




}
