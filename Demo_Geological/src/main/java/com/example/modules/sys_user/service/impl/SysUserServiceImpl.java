package com.example.modules.sys_user.service.impl;

import com.example.common.security.LoginUser;
import com.example.common.tools.JwtUtil;
import com.example.common.tools.Redis.RedisCache;
import com.example.common.tools.web.ResponseResult;
import com.example.modules.sys_user.model.SysUser;
import com.example.modules.sys_user.mapper.SysUserMapper;
import com.example.modules.sys_user.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Aerlany
 * @since 2024-04-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    // 用户登录接口
    @Override
    public ResponseResult Login(SysUser user) {
        // 从容器中拿出 AuthenticationManager 实现 用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 认证没通过,给出对应的提示
        if (Objects.isNull(authenticate)) {
            return new ResponseResult<>(200, "登录失败");
//            throw new RuntimeException("登录失败");
        }

        //认证通过生成JWT,将其存入ResponseResult传给前端
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        //将JWT保存到redis中
        redisCache.setCacheObject("login" + userId, loginUser);

        return new ResponseResult<>(200, "true", map);
    }

    @Override
    public ResponseResult logout() {
        //在SecurityContextHolder中取出当前用户的ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();

        redisCache.deleteObject("login" + id);


        return new ResponseResult<>(200, "Logout Secuess");
    }
}
