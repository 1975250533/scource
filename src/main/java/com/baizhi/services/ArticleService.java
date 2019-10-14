package com.baizhi.services;

import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {
    public Map< String, Object > queryAll(Integer page, Integer rows);


    //添加
    public void add(Article article);

    //修改
    public void update(Article article);
}
