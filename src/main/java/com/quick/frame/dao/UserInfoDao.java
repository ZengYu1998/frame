package com.quick.frame.dao;

import com.quick.frame.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengyu
 * @since 2020-12-08
 */
public interface UserInfoDao extends BaseMapper<UserInfo> {

    /**
     * 通过账号查询出用户信息 (账号是唯一的)
     * @param accountNumber -账号
     * @return -用户信息实体类
     */
    @Select("select * from user_info where account_number=#{accountNumber}")
    UserInfo getUserInfoInAccountNumber(String accountNumber);

}
