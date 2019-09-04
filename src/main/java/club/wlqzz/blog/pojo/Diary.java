package club.wlqzz.blog.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@ToString
public class Diary {
    @Getter@Setter private int id;
    @Getter@Setter private String title;
    @Getter@Setter private String context;
    @Getter@Setter private int userId;
    @Getter@Setter private Date date;
}
