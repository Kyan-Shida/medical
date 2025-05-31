package com.ryan.medical.Controller;

import com.ryan.medical.Service.LoginService;
import com.ryan.medical.pojo.LoginResult;
import com.ryan.medical.pojo.Result;
import com.ryan.medical.pojo.User;

import com.ryan.medical.utils.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@Slf4j
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/api/login")
    public Result list(@RequestBody User data){
        log.info("user{}",data);
        User user = loginService.login(data);
        if(user!=null){
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            String token= JwtUtils.generateJwt(claims);
            LoginResult result=new LoginResult(user.getId(), token,user.getTimes());
            return Result.success(result);
        }
        return Result.error("用户名或密码错误");
    }
}
