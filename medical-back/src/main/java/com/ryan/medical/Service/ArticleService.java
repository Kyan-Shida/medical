package com.ryan.medical.Service;

import com.ryan.medical.Mapper.ArticleMapper;
import com.ryan.medical.pojo.Article;
import com.ryan.medical.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public Page getArticles(Integer now, Integer size, String searchQuery, String category) {
        // 计算分页的起始位置
        int start = (now - 1) * size;

        // 获取符合条件的文章列表
        List<Article> articles = articleMapper.findArticles(start, size, searchQuery, category);

        // 获取符合条件的文章总数
        int total = articleMapper.countArticles(searchQuery, category);

        // 返回分页对象
        return new Page(articles, total);
    }
}
