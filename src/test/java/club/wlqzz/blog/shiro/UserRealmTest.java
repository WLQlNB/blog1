package club.wlqzz.blog.shiro;

import club.wlqzz.blog.util.Md5Class;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserRealmTest {

    @Test
    public void test(){
        String password = Md5Class.md5("123456","20190001");
        System.out.println(password);
    }

}