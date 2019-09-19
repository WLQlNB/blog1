package club.wlqzz.blog;

import club.wlqzz.blog.pojo.Diary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1(){
        Diary diary=new Diary();
        diary.setTitle("aa");
        System.out.println(diary.getTitle());
        System.out.println(diary);
    }

    @Test
    public void testRedis(){
        stringRedisTemplate.opsForValue().set("hello", "world");
        String ans =stringRedisTemplate.opsForValue().get("hello");
        System.out.println(ans);
    }
}
