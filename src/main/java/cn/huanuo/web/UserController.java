package cn.huanuo.web;

import cn.huanuo.entity.SysUser;
import cn.huanuo.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    UserService userService;


    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hellopage.html";
    }

    @RequestMapping("/home")
    public String home() {
        return "homepage.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "loginpage.html";
    }

    @ResponseBody
    @GetMapping("users")
    public List<SysUser> getUsers() {
        return userService.getUsers();
    }
}
