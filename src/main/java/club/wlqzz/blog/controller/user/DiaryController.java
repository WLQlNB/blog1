package club.wlqzz.blog.controller.user;


import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.Diary;
import club.wlqzz.blog.pojo.User;
import club.wlqzz.blog.service.DiaryService;
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
@RequestMapping("/user")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @GetMapping("/diary")
    public String toDiaryPage(HttpSession session,Model model) throws Exception {
       User user= (User) session.getAttribute("loginUser");
        List<Diary> diaryList = diaryService.selectAllDiary(user.getId());
        Collections.reverse(diaryList);
        model.addAttribute("diaryList", diaryList);
        return "diary";
    }

    @GetMapping("/lookDiary/{id}")
    public String lookDiary(@PathVariable("id") Integer id, Model model) throws Exception {
        Diary diary = diaryService.selectDiary(id);
        model.addAttribute("diary", diary);
        return "lookDiary";
    }

    @GetMapping("/writeDiary")
    public String writeDiary() {
        return "user/writeDiary";
    }

    @PostMapping("/addDiary")
    public String addDiary(Diary diary, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("loginUser");
        diary.setUserId(user.getId());
        diaryService.addDiary(diary);
        return "redirect:/user/myDiary";
    }

    @GetMapping("/editDiary/{id}")
    public String editDiary(@PathVariable("id") Integer id, Model model) throws Exception {
        Diary diary = diaryService.selectDiary(id);
        model.addAttribute("diary", diary);
        return "user/editDiary";
    }

    @PostMapping("/updateDiary")
    public String updateDiary(Diary diary) throws Exception {
        diaryService.updateDiary(diary);
        return "redirect:/user/myDiary";
    }

    @GetMapping("/deleteDiary")
    public String deleteDiary(Diary diary) throws Exception {
        diaryService.deleteDiary(diary.getId());
        return "redirect:/user/myDiary";
    }

    @GetMapping("/myDiary")
    public String myDiaryList(HttpSession session, Model model,@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) throws Exception {
        User user = (User) session.getAttribute("loginUser");
        PageHelper.startPage(pageNum, 5);
        List<Diary> diaryList = diaryService.selectAllDiary(user.getId());
        Collections.reverse(diaryList);
        PageInfo<Diary> pageInfo = new PageInfo<>(diaryList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("diaryList", diaryList);
        return "user/diaryList";
    }


}
