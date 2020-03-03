package club.wlqzz.blog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 角色对应实体类
 */
@Data
public class Role implements Serializable {
    private Integer id; // 编号
    private String name; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private Set<Permission> permissions;
    private Set<User> userInfos;// 一个角色对应多个用户

}
