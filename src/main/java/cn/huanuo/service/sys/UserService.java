package cn.huanuo.service.sys;

import cn.huanuo.dao.sys.SysUserRepository;
import cn.huanuo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    SysUserRepository sysUserRepository;

    public List<SysUser> getUsers() {
        List<SysUser> users = sysUserRepository.findAll();
        List<SysUser> result = new ArrayList<>();
        for (SysUser user : users) {
            user.setPassword(null);
            result.add(user);
        }
        return result;
    }
}
