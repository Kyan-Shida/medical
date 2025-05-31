package com.ryan.medical.Mapper;

import com.ryan.medical.pojo.Patient;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PatientMapper {

    @Insert("INSERT INTO patient (name, gender, age, department, visit_time, phone, address, complaints, current_history, past_history, allergy_history, diagnosis) " +
            "VALUES (#{name}, #{gender}, #{age}, #{department}, #{visitTime}, #{phone}, #{address}, #{complaints}, #{currentHistory}, #{pastHistory}, #{allergyHistory}, #{diagnosis})")
    void insertPatient( Patient patient);
}
