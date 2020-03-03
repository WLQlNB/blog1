package club.wlqzz.blog.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Reply implements Serializable {
    private int id;
    private String context;
    private int commentId;
    private int blogId;
}
