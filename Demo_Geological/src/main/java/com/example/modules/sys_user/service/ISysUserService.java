package com.example.modules.sys_user.service;

import com.example.common.tools.web.ResponseResult;
import com.example.modules.sys_user.model.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Aerlany
 * @since 2024-04-15
 */
public interface ISysUserService extends IService<SysUser> {

    ResponseResult Login(SysUser user);

    ResponseResult logout();
}
