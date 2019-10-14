package com.baizhi.mapper;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AlbumMapper {
    //查询
    public List< Album > findAllAlbum(@Param("begin") Integer begin, @Param("rows") Integer rows);

    public Integer count();


    //添加
    public void add(Album album);

    //修改图片路径
    public void updateUrl(Album album);

    public void updateMsg(Album album);

    public void del(String[] id);

    //修改数量
    public void updateCount(Album album);

}
