package com.quick.frame;

import com.quick.frame.commons.util.JwtUtil;
import com.quick.frame.dao.UserInfoDao;
import com.quick.frame.entity.UserInfo;
import com.quick.frame.service.IUserInfoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @description: SpringBoot单元测试
 * @author: znegyu
 * @create: 2020-11-23 09:54
 **/
@SpringBootTest
public class BootTest {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private IUserInfoService userInfoService;

    @Resource
    private UserInfoDao userInfoDao;

    @Test
    void test_1() {
        User user=new User(UUID.randomUUID(),LocalDateTime.now(),"曾煜");
        redisTemplate.opsForValue().set("data",user);
    }

    @Test
    void test_2() {
        User user= (User) redisTemplate.opsForValue().get("data");
        System.out.println(user);
    }

    @Test
    void test_3() {
            System.out.println(jwtUtil.createToken("1234"));
    }

    @Test
    void test_4(){
        jwtUtil.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ6ZW5neXUiLCJ1c2VySWQiOiIxMjM0IiwiaWF0IjoxNjA2MzczNzY3fQ.n-HeTEE0hjlclm2DzGEpBlfJdLikh4z0CjMO_AABBuA");
    }

    @Test
    void name() {
        UserInfo userInfoInAccountNumber = userInfoDao.getUserInfoInAccountNumber("13232094481");
//        UserInfo userInfo=new UserInfo();
//        userInfo.setAccountNumber("13232094481");
//        userInfo.setUserName("曾煜");
//        userInfo.setLoginPassword("123456");
//        userInfoService.save(userInfo);
    }
}


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class User{
    private UUID id;
    private LocalDateTime createTime;
    private String name;
}
