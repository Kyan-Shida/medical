package com.ryan.medical.Service;

import com.ryan.medical.pojo.Hospital;
import java.util.List;

public interface HospitalInterface {

    // 查询医院列表
    List<Hospital> getHospitals(String region, int page, int size);

    // 获取医院总数
    int getHospitalCount(String region);

    List<Hospital> all();
}
