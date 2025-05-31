package com.ryan.medical.Controller;

import com.ryan.medical.Service.UpdateService;
import com.ryan.medical.pojo.Result;
import com.ryan.medical.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin("*")
public class UpdateController {
    @Autowired
    private UpdateService updateService;
    @PostMapping("/api/update")
    public Result update_user(@RequestBody User data){
        updateService.update_user(data);
        return Result.success();
    }

}
