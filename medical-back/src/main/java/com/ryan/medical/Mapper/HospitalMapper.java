package com.ryan.medical.Mapper;

import com.ryan.medical.pojo.Hospital;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HospitalMapper {

    @Select({
            "<script>",
            "SELECT * FROM hospital",
            "<where>",
            "  <if test='region != null and region != \"\"'>AND address LIKE CONCAT('%', #{region}, '%')</if>",
            "</where>",
            "LIMIT #{page}, #{size}",
            "</script>"
    })
    List<Hospital> searchHospitals( String region,
                                    int page,
                                    int size);

    // 获取医院总数
    @Select("<script>" +
            "SELECT COUNT(*) FROM hospital " +
            "<where>" +
            "  <if test='region != null and region != \"\"'>AND address LIKE CONCAT('%', #{region}, '%')</if>" +
            "</where>" +
            "</script>")
    int countHospitals(String region);

    @Select("SELECT * from hospital")
    List<Hospital> all();
}
