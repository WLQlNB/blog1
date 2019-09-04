package club.wlqzz.blog.service.impl;

import club.wlqzz.blog.mapper.DiaryMapper;
import club.wlqzz.blog.pojo.Diary;
import club.wlqzz.blog.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryMapper diaryMapper;

    @Override
    public void addDiary(Diary diary) throws Exception {
        diaryMapper.insert(diary);
    }

    @Override
    public void deleteDiary(Integer id) throws Exception {
        diaryMapper.delete(id);
    }

    @Override
    public void updateDiary(Diary diary) throws Exception {
        diaryMapper.update(diary);
    }

    @Override
    public Diary selectDiary(Integer id) throws Exception {
        return diaryMapper.selectById(id);
    }

    @Override
    public List<Diary> selectAll() throws Exception {
        return diaryMapper.selectAll();
    }


    @Override
    public List<Diary> selectAllDiary(Integer userId) throws Exception {
        return diaryMapper.selectAllDiary(userId);
    }
}
