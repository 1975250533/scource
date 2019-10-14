package com.baizhi.services;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterServices {
    //查询
    public Map< String, Object > findAllChapter(Integer page, Integer rows, String albumId);


    public Integer count(String albumId);


    //添加
    public String add(Chapter chapter);

    public void updateFilepath(Chapter chapter);

    public void update(Chapter chapter);

    public void del(String[] id);


}
