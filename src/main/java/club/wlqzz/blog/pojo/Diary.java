package club.wlqzz.blog.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Diary {
    private int id;
    private String title;
    private String context;
    private int userId;
    private Date date;
}
