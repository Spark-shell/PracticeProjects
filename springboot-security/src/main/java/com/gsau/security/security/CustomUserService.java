package com.gsau.security.security;

import com.gsau.security.dao.UserDao;
import com.gsau.security.domain.SysRole;
import com.gsau.security.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;


/**
 * @author WangGuoQing
 * @date 2019/4/10 16:14
 * @Desc 用户信息服务层
 */
@Service
public class CustomUserService implements UserDetailsService { //自定义UserDetailsService 接口

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) { //重写loadUserByUsername 方法获得 userdetails 类型用户
        SysUser user = userDao.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        for(SysRole role:user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println(role.getName());
        }
        return new User(
                user.getUsername(),
                user.getPassword(), authorities);

    }

}
