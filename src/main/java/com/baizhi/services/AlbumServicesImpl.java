package com.baizhi.services;

import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AlbumServicesImpl implements AlbumServices {
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map< String, Object > findAllAlbum(Integer page, Integer rows) {
        Map< String, Object > map = new HashMap<>();
        //获取数据库中的起始条
        Integer begin = (page - 1) * rows;
        //查询出轮播图列表
        List< Album > list = albumMapper.findAllAlbum(begin, rows);
        //查询出总条数
        Integer records = albumMapper.count();
        //总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);
        map.put("records", records);
        map.put("page", page);
        map.put("rows", list);
        //总页数  total
        //当前页  page
        //录播图列表 rows
        //总条数   records

        return map;
    }

    @Override
    public String add(Album album) {
        String s = UUID.randomUUID().toString();
        album.setId(s);
        album.setPublishDate(new Date());
        albumMapper.add(album);
        return s;
    }

    @Override
    public void updateUrl(Album album) {

        albumMapper.updateUrl(album);
    }

    @Override
    public void updateMsg(Album album) {
        albumMapper.updateMsg(album);
    }


    @Override
    public void del(String[] id) {
        albumMapper.del(id);
    }
}
