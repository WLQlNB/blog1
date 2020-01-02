package club.wlqzz.blog.pojo;

import lombok.Data;

import java.util.Set;

/**
 * 角色对应实体类
 */
@Data
public class Role {
    private String id;
    private String roleName;
    private Set<Permissions> permissions;
}
