package club.wlqzz.blog.service;

import club.wlqzz.blog.pojo.Blog;
import club.wlqzz.blog.pojo.Diary;

import java.util.List;

public interface DiaryService {
    void addDiary(Diary diary) throws Exception;

    void deleteDiary(Integer id) throws Exception;

    void updateDiary(Diary diary) throws Exception;

    Diary selectDiary(Integer id) throws Exception;

    List<Diary> selectAll() throws Exception;

    List<Diary> selectAllDiary(Integer userId) throws Exception;

}
