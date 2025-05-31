package com.ryan.medical.Mapper;

import com.ryan.medical.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {
    @Insert("INSERT INTO user (username, password,times, create_time, update_time) VALUES (#{username}, #{password}, #{times},#{createTime}, #{updateTime})")
    void register(User data);
}
