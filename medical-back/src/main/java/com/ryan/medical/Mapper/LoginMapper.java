package com.ryan.medical.Mapper;

import com.ryan.medical.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select * from user where username=#{username} and password=#{password}")
    User login(User data);
}
