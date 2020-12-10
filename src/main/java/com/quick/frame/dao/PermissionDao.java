package com.quick.frame.dao;

import com.quick.frame.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengyu
 * @since 2020-12-08
 */
public interface PermissionDao extends BaseMapper<Permission> {

    /**
     * 获取指定账号的所有权限信息
     * @param accountNumber -账号
     * @return -权限信息列表
     */
    List<Permission> getPermissionListInAccountNumber(String accountNumber);

}
