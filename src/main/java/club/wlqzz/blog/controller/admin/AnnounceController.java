package club.wlqzz.blog.controller.admin;

import club.wlqzz.blog.pojo.Announcement;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.AnnouceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnnounceController {

    @Autowired
    private AnnouceService annouceService;

    @GetMapping("/admin/announcement")
    public String announcement() {
        return "admin/announcement";
    }

    @PostMapping("/admin/annouce")
    public String annouce(Announcement announcement) throws Exception {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        announcement.setUserId(user.getId());
        announcement.setCount(0);
        annouceService.insertAnnoucement(announcement);
        return "redirect:/admin/announceManage";
    }

    @GetMapping("/admin/announceManage")
    public String announceManage(Model model, @RequestParam(value = "pageNum",
            defaultValue = "1") Integer pageNum) throws Exception {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(pageNum, 5);
        List<Announcement> aList = annouceService.selectAnnoucements(user.getId());
        PageInfo<Announcement> pageInfo = new PageInfo<>(aList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("aList", aList);
        return "admin/myAnnounce";
    }

    @GetMapping("/admin/toAnnounce")
    public String toAnnounce(Integer id, Model model) throws Exception {
        Announcement announcement = annouceService.selectAnnoucement(id);
        model.addAttribute("announcement", announcement);
        return "/admin/editAnnounce";
    }

    @PostMapping("/admin/updateAnnounce")
    public String updateAnnounce(Announcement announcement) throws Exception {
        System.out.println("gaaaaaaaaaaaaaaaaaaaaaa"+announcement);
        annouceService.updateAnnoucement(announcement);
        return "redirect:/admin/announceManage";
    }

    @GetMapping("/admin/deleteAnnounce")
    public String deleteAnnounce(Integer id) throws Exception {
        annouceService.deleteAnnoucement(id);
        return "redirect:/admin/announceManage";
    }

}
