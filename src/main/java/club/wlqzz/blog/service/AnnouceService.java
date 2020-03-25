package club.wlqzz.blog.service;

import club.wlqzz.blog.pojo.Announcement;

import java.util.List;

public interface AnnouceService {
    void insertAnnoucement(Announcement announcement)throws Exception;

    void deleteAnnoucement(Integer id)throws Exception;

    void updateAnnoucement(Announcement announcement)throws Exception;

    List<Announcement> selectAnnoucements(Integer id)throws Exception;

    List<Announcement> selectAll()throws Exception;

    Announcement selectAnnoucement(Integer id)throws Exception;
}
