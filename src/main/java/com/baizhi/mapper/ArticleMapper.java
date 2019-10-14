package com.baizhi.mapper;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    public List< Article > queryAll(@Param("start") Integer start, @Param("rows") Integer rows);

    public Integer getCount();


    //添加
    public void add(Article article);

    //修改
    public void update(Article article);
}
