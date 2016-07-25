import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/7/24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testFindById(){
        User user=userService.findUserById(110);
        System.out.println(user);
    }

    @Test
    public void testSaveUser(){
        User user=new User(110,"lisi",99.9F);
        userService.testSaveUser(user);

        System.out.println(user);
    }

}
