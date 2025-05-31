package com.ryan.medical.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {
    private Integer id;
    private String name;  // 医院名称
    private String type;  // 医院类型
    private String advantages;  // 医院优势
    private String beds;  // 床位数
    private String yearfounded;  // 创建年份
    private String address;  // 医院地址
    private String link;  // 医院链接
}
