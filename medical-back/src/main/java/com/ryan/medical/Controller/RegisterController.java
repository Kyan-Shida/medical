package com.ryan.medical.Controller;

import com.ryan.medical.Service.RegisterService;
import com.ryan.medical.pojo.Result;
import com.ryan.medical.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @PostMapping("/api/register")
    public Result register(@RequestBody User data){
        registerService.register(data);
        return Result.success();
    }
}
