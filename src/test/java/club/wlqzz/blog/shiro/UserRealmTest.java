package club.wlqzz.blog.shiro;

import club.wlqzz.blog.util.Md5Class;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserRealmTest {

    @Test
    public void test(){
        String password = Md5Class.md5("123456","1000");
        System.out.println(password);
    }

    @Test
    public void testPwd(){
        ByteSource credentialsSalt = ByteSource.Util.bytes(String.valueOf(20190016));
        String md5Password = new Md5Hash(String.valueOf(123456), credentialsSalt,1024).toString();
        System.out.println(md5Password);
    }

}