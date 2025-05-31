package com.ryan.medical.Mapper;

import com.ryan.medical.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TimeMapper {
    @Update("UPDATE user SET times = #{times} WHERE username = #{username}")
    void set_times(User data);


    @Select("SELECT times from user where username=#{username}")
    Integer show_time(String username);
}
