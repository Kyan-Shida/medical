package com.ryan.medical.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalResponse {
    private List<Hospital> hospitals;  // 医院列表
    private int totalCount;  // 医院总数
}
