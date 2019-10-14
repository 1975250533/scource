package com.baizhi.mapper;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ChapterMapper {
    //查询
    public List< Chapter > findAllChapter(@Param("begin") Integer begin, @Param("rows") Integer rows, String albumId);

    public Integer count(String albumId);

    //添加
    public void add(Chapter chapter);

    //修改音频路径
    public void updateFilepath(Chapter chapter);

    //修改
    public void update(Chapter chapter);

    public void del(String[] id);


}
