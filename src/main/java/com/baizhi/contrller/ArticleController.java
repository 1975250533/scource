package com.baizhi.contrller;

import com.baizhi.entity.Article;
import com.baizhi.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("getAll")
    public Map< String, Object > getAll(Integer page, Integer rows) {
        Map< String, Object > map = articleService.queryAll(page, rows);
        return map;
    }

    @RequestMapping("add")
    public void getAll(Article article) {
        articleService.add(article);
        System.out.println(article);
    }

    @RequestMapping("update")
    public void update(Article article) {
        articleService.update(article);
        System.out.println(article);
    }
}
