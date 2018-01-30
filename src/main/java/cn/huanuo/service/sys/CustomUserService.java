package cn.huanuo.service.sys;

import cn.huanuo.dao.sys.SysUserRepository;
import cn.huanuo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        SysUser user = sysUserRepository.findByUsername(s);

        if (user == null)
            throw new UsernameNotFoundException("user not exists");
        System.out.println(user.getRoles());
        return user;
    }
}
