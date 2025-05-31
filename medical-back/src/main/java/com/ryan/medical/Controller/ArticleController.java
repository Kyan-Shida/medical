package com.ryan.medical.Controller;

import com.ryan.medical.Service.ArticleService;
import com.ryan.medical.pojo.Article;
import com.ryan.medical.pojo.Page;
import com.ryan.medical.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // 获取所有文章，并支持搜索和分类筛选
    @GetMapping("/api/articles")
    public Result getAllArticles(@RequestParam Integer now_page,
                                 @RequestParam Integer page_size,
                                 @RequestParam(required = false) String search_query,
                                 @RequestParam(required = false) String category) {
        log.info("当前页: {}, 每页显示: {}, 搜索关键词: {}, 分类: {}", now_page, page_size, search_query, category);

        // 调用服务层方法，传入搜索和分类参数
        Page result_article = articleService.getArticles(now_page, page_size, search_query, category);

        return Result.success(result_article); // 返回查询结果
    }
}
