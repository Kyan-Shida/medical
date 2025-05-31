package com.ryan.medical.Controller;

import com.ryan.medical.Service.TimeService;
import com.ryan.medical.pojo.Result;
import com.ryan.medical.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@Slf4j
public class TimeController {
    @Autowired
   private TimeService timeService;
    @PostMapping("/api/times")
    public Result set_time(@RequestBody User data){
        timeService.set_times(data);
        return Result.success();
    }

    @GetMapping("/api/times")
    public Result show_time(@RequestParam String username){
        return Result.success(timeService.show_time(username));
        }
}
