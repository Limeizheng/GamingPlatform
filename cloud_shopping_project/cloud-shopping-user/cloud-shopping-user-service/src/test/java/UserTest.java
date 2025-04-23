import com.cloud.shopping.UserApplication;
import com.cloud.shopping.user.mapper.UserMapper;
import com.cloud.shopping.user.pojo.User;
import com.cloud.shopping.user.utils.CodecUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * add 5000 users
     */
    @Test
    public void addUser(){
        User user = new User();
        user.setId(null);
        user.setCreated(new Date());
        user.setPhone("1883482");
        user.setUsername("Trump");
        user.setPassword("123456");
        String encodePassword = CodecUtils.passwordBcryptEncode(user,user.getPassword().trim());
        user.setPassword(encodePassword);
        this.userMapper.insertSelective(user);
    }

    /**
     * Add back-end management personnel
     */
    @Test
    public void addAdmin(){
        User user = new User();
        user.setCreated(new Date());
        user.setPhone("88888888");
        user.setUsername("111");
        user.setPassword("111");
        String encodePassword = CodecUtils.passwordBcryptEncode(user,user.getPassword().trim());
        user.setPassword(encodePassword);
        userMapper.insertSelective(user);
    }

}
