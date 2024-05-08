package com.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.sys_user.mapper.SysUserMapper;
import com.example.modules.sys_user.model.SysUser;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.jdbc.Null;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class DemoGeologicalApplicationTests {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    void contextLoads() {
        QueryWrapper<SysUser> user = new QueryWrapper<>();
        List<SysUser> sysUsers = sysUserMapper.selectList(user);
        System.out.println(sysUsers);
    }

    //校验Spring Security密码加密策略
    @Test
    public void SecurityPasswdCheack() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }

}
