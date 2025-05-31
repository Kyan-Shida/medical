package com.ryan.medical.Mapper;

import com.ryan.medical.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UpdateMapper {
    @Update("UPDATE user SET username = #{username}, password = #{password},update_time=#{updateTime} WHERE id = #{id}")
    void update_user(User data);
}
