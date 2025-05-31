package com.ryan.medical.Service;

import com.ryan.medical.Mapper.RegisterMapper;
import com.ryan.medical.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegisterService implements RegisterInterface{
    @Autowired
    private RegisterMapper registerMapper;
    @Override
    public void register(User data) {
        data.setTimes(10);
        data.setCreateTime(LocalDateTime.now());
        data.setUpdateTime(LocalDateTime.now());
         registerMapper.register(data);
    }
}
