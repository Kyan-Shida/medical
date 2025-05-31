package com.ryan.medical.Controller;

import com.ryan.medical.Service.HospitalService;
import com.ryan.medical.pojo.Hospital;
import com.ryan.medical.pojo.HospitalResponse;
import com.ryan.medical.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*") // 允许跨域请求，允许所有来源
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    // 查询医院列表
    @GetMapping("/api/hospital")
    public Result getHospitals(
            @RequestParam(required = false) String query,  // 可选的搜索条件，如医院名称
            @RequestParam(required = false) String category, // 可选的分类，如医院等级
            @RequestParam(required = false) String region,   // 可选的地区条件
            @RequestParam(defaultValue = "0") int page,     // 默认页数为0
            @RequestParam(defaultValue = "10") int size      // 默认每页显示10条数据
    ) {
            // 调用服务层方法获取医院列表
            List<Hospital> hospitals = hospitalService.getHospitals(region, page, size);
            log.info("hospitals:{}",hospitals);
            // 获取医院总数
            int totalCount = hospitalService.getHospitalCount(region);
            HospitalResponse res=new HospitalResponse(hospitals, totalCount);
            log.info("res:{}",res);
            // 返回结果，包含医院数据和总数
            return Result.success(res);
    }

    // 你可以根据需要再添加其他功能，如获取单个医院的详细信息等
    @GetMapping("/api/all")
    public Result get() {

        return Result.success(hospitalService.all());
    }
}
