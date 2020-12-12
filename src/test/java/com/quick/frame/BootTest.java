package com.quick.frame;

import com.quick.frame.commons.util.JwtUtil;
import com.quick.frame.dao.*;
import com.quick.frame.entity.*;
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

    @Resource
    private RoleDao roleDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private PermissionDao permissionDao;

    @Resource
    private RolePermissionDao rolePermissionDao;

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
           // System.out.println(jwtUtil.createToken("1234"));
    }

    @Test
    void test_4(){
        //jwtUtil.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ6ZW5neXUiLCJ1c2VySWQiOiIxMjM0IiwiaWF0IjoxNjA2MzczNzY3fQ.n-HeTEE0hjlclm2DzGEpBlfJdLikh4z0CjMO_AABBuA");
    }

    @Test
    void name() {
        UserInfo userInfo =new UserInfo();
        userInfo.setAccountNumber("13030106617");
        userInfo.setLoginPassword("123456");
        userInfo.setUserName("曾煜2号");
        //userInfoDao.insert(userInfo);
        UserInfo userInfoInAccountNumber = userInfoDao.getUserInfoInAccountNumber("13232094481");
        Role role =new Role();
        role.setRoleName("admin");
        role.setDescription("超级管理员");
        //roleDao.insert(role);
        UserRole userRole=new UserRole();
        userRole.setUserId("a3c3ac610fba75d38b0bafd5bfe519bc");
        userRole.setRoleId("3beb19711a5785f7f5891c73484c7056");
        userRoleDao.insert(userRole);
        Permission permission=new Permission();
        permission.setPermissionName("/test_1");
        permission.setDescription("访问test_1接口的权限");
        //permissionDao.insert(permission);
        permission.setId(null);
        permission.setPermissionName("/api/userRole/addRoleToUser");
        permission.setDescription("为用户添加角色的权限");
        //permissionDao.insert(permission);
        permission.setId(null);
        permission.setPermissionName("/test");
        permission.setDescription("访问test接口的权限");
        //permissionDao.insert(permission)
        RolePermission rolePermission=new RolePermission();
        rolePermission.setRoleId("3beb19711a5785f7f5891c73484c7056");
        rolePermission.setPermissionId("4596e87b5a9c2012f897c18470938b1f");
        //rolePermissionDao.insert(rolePermission);
        rolePermission.setId(null);
        rolePermission.setPermissionId("47470214997c3b531f7ba6afe36ab727");
        //rolePermissionDao.insert(rolePermission);
        rolePermission.setId(null);
        rolePermission.setPermissionId("ea7034e0136e1722ea4e0eacc3244f5c");
        //rolePermissionDao.insert(rolePermission);
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
