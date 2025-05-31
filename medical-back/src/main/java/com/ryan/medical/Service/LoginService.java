package com.ryan.medical.Service;


import com.ryan.medical.Mapper.LoginMapper;
import com.ryan.medical.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginInterface {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public User login(User data) {
        return loginMapper.login(data);
    }
}
