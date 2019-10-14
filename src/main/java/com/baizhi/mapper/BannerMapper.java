package com.baizhi.mapper;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {
    //查询
    public List< Banner > queryAll(@Param("begin") Integer begin, @Param("rows") Integer rows);

    public Integer count();

    //添加
    public void add(Banner banner);

    //修改图片路径
    public void updateUrl(Banner banner);

    public void updateStatus(Banner banner);

    public void del(String[] id);


    public List< Banner > query();
}
