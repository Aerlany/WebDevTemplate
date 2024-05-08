package com.example.modules.sys_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.tools.web.ResponseResult;
import com.example.modules.sys_user.model.SysUser;
import com.example.modules.sys_user.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Aerlany
 * @since 2024-04-15
 */
@RestController
@Api(tags = "用户接口")
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService service;

    @PostMapping("/login")
    public ResponseResult Login(@RequestBody SysUser user) {
        return service.Login(user);
    }

    @PostMapping("/logout")
    public ResponseResult Logout() {
        return service.logout();
    }
}
