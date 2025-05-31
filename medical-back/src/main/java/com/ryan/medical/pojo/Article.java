package com.ryan.medical.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Long id; // 文章 ID
    private String title; // 文章标题
    private String content; // 文章内容
    private String category;//分类
    private String imageUrl;  // 新添加的字段
}
