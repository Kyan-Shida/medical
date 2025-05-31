package com.ryan.medical.Service;

import com.ryan.medical.Mapper.HospitalMapper;
import com.ryan.medical.pojo.Hospital;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class HospitalService implements HospitalInterface {

    @Autowired
    private HospitalMapper hospitalMapper;



    // 获取医院列表，支持分页和筛选
    @Override
    public List<Hospital> getHospitals(String region, int page, int size) {
        // 转换分页参数：page 是当前页，size 是每页数量
        int offset = page * size;  // 计算偏移量
        List<Hospital> res = hospitalMapper.searchHospitals(region, offset, size);
        return res;
    }

    // 获取医院总数，用于分页
    @Override
    public int getHospitalCount( String region) {
        return hospitalMapper.countHospitals( region);
    }
    @Override
    public List<Hospital> all() {
        List<Hospital> res=hospitalMapper.all();
        log.info("{}",res);
        return res;
    }
}
