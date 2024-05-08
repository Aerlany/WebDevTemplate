package com.example.common.security;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.modules.sys_user.model.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private SysUser user;

    private List<String> permission;

    public LoginUser(SysUser user, List<String> permission) {
        this.user = user;
        this.permission = permission;
    }

    // 该属性不进行序列化
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //将permission中的字符串转换为GrantedAuthority的实现类对象SimpleGrantedAuthority
        if (authorities != null) {
            return authorities;
        }

        // Version1
//        List<GrantedAuthority> authorityList = new ArrayList<>();
//        for (String string : permission) {
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("");
//            authorityList.add(authority);
//        }

        //version2
        authorities = permission.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
