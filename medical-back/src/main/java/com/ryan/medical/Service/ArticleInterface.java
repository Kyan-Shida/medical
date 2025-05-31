package com.ryan.medical.Service;

import com.ryan.medical.pojo.Page;

public interface ArticleInterface {

    // 添加了 search_query 和 category 两个筛选参数
    Page getArticles(Integer now, Integer size, String search_query, String category);

}
