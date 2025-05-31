package com.ryan.medical.Service;

import com.ryan.medical.Mapper.TimeMapper;
import com.ryan.medical.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeService implements TimeInterface{
    @Autowired
    private TimeMapper timeMapper;


    @Override
    public void set_times(User data) {
        timeMapper.set_times(data);
    }

    @Override
    public Integer show_time(String username) {
        return timeMapper.show_time(username);
    }

}
