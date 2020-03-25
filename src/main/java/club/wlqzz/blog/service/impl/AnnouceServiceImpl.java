package club.wlqzz.blog.service.impl;

import club.wlqzz.blog.mapper.AnnouceMapper;
import club.wlqzz.blog.pojo.Announcement;
import club.wlqzz.blog.service.AnnouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouceServiceImpl implements AnnouceService {

    @Autowired
    private AnnouceMapper annouceMapper;

    @Override
    public void insertAnnoucement(Announcement announcement) throws Exception {
        annouceMapper.insertAnnoucement(announcement);
    }

    @Override
    public void deleteAnnoucement(Integer id) throws Exception {
        annouceMapper.deleteAnnoucement(id);
    }

    @Override
    public void updateAnnoucement(Announcement announcement) throws Exception {
        annouceMapper.updateAnnoucement(announcement);
    }

    @Override
    public List<Announcement> selectAnnoucements(Integer id) throws Exception {
        return annouceMapper.selectAnnoucements(id);
    }

    @Override
    public List<Announcement> selectAll() throws Exception {
        return annouceMapper.selectAll();
    }

    @Override
    public Announcement selectAnnoucement(Integer id) throws Exception {
        return annouceMapper.selectAnnoucement(id);
    }
}
