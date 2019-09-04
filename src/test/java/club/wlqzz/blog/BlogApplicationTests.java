package club.wlqzz.blog;

import club.wlqzz.blog.pojo.Diary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

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
}
