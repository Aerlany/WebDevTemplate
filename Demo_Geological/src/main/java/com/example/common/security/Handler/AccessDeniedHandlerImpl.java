package com.example.common.security.Handler;

import com.alibaba.fastjson.JSON;
import com.example.common.tools.web.ResponseResult;
import com.example.common.tools.web.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //处理异常
        ResponseResult result = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "权限认证失败！用户无访问权限。", false);
        String jsonString = JSON.toJSONString(result);
        WebUtil.renderString(response, jsonString);
    }
}
