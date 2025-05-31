package com.ryan.medical.Service;

import com.ryan.medical.Mapper.UpdateMapper;
import com.ryan.medical.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateService implements UpdateInterface{
    @Autowired
    private UpdateMapper updateMapper;
    @Override
    public void update_user(User data) {
        data.setUpdateTime(LocalDateTime.now());
        updateMapper.update_user(data);
    }
}
