package com.example.common.security.Handler;

import com.alibaba.fastjson.JSON;
import com.example.common.tools.web.ResponseResult;
import com.example.common.tools.web.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //处理异常
        ResponseResult result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "用户认证失败！请重新登录。",false);
        String jsonString = JSON.toJSONString(result);
        WebUtil.renderString(response, jsonString);
    }
}
