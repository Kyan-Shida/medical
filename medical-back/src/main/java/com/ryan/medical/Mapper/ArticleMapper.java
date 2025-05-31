package com.ryan.medical.Mapper;

import com.ryan.medical.pojo.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface ArticleMapper {

    // 根据分页、搜索关键词和分类筛选文章
    @Select("<script>" +
            "SELECT * FROM articles " +
            "WHERE 1=1 " +
            "<if test='search_query != null and search_query != \"\"'>" +
            "AND (title LIKE CONCAT('%',#{search_query},'%') OR content LIKE CONCAT('%',#{search_query},'%')) " +
            "</if>" +
            "<if test='category != null and category != \"\"'>" +
            "AND category = #{category} " +
            "</if>" +
            "LIMIT #{start}, #{size}" +
            "</script>")
    List<Article> findArticles(@Param("start") Integer start, @Param("size") Integer size,
                               @Param("search_query") String searchQuery, @Param("category") String category);

    // 计算符合条件的文章总数
    @Select("<script>" +
            "SELECT count(*) FROM articles " +
            "WHERE 1=1 " +
            "<if test='search_query != null and search_query != \"\"'>" +
            "AND (title LIKE CONCAT('%',#{search_query},'%') OR content LIKE CONCAT('%',#{search_query},'%')) " +
            "</if>" +
            "<if test='category != null and category != \"\"'>" +
            "AND category = #{category} " +
            "</if>" +
            "</script>")
    Integer countArticles(@Param("search_query") String searchQuery, @Param("category") String category);

}
