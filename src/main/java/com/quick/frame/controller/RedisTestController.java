package com.quick.frame.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @description: Redis热部署反序列化测试
 * @author: znegyu
 * @create: 2020-11-23 10:52
 **/
@RestController
@RequestMapping("/api")
public class RedisTestController {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(){
        try {
            User user = new User(UUID.randomUUID(), LocalDateTime.now(),"曾煜");
            redisTemplate.opsForValue().set("data",user);
            return "23";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public User get(){
        User user= (User) redisTemplate.opsForValue().get("data");
        return user;
    }
}


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class User{
    private UUID id;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private String name;
}


