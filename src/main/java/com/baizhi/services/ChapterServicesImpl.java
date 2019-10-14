package com.baizhi.services;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ChapterServicesImpl implements ChapterServices {
    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private AlbumMapper albumMapper;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map< String, Object > findAllChapter(Integer page, Integer rows, String albumId) {

        Map< String, Object > map = new HashMap<>();
        //获取数据库中的起始条
        Integer begin = (page - 1) * rows;
        //查询出轮播图列表
        List< Chapter > list = chapterMapper.findAllChapter(begin, rows, albumId);
        System.out.println(begin + "==begin");
        System.out.println(rows + "++++rows");
        System.out.println(list + "==========list");
        //查询出总条数
        Integer records = chapterMapper.count(albumId);
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
    public Integer count(String albumId) {
        Integer count = chapterMapper.count(albumId);
        return count;
    }

    @Override
    public String add(Chapter chapter) {
        String s = UUID.randomUUID().toString();
        chapter.setId(s);
        chapter.setCreateDate(new Date());
        chapterMapper.add(chapter);
        return s;
    }


    @Override
    public void updateFilepath(Chapter chapter) {
        chapterMapper.updateFilepath(chapter);
        Integer count = chapterMapper.count(chapter.getAlbumId());
        Album album = new Album();
        album.setCount(count);
        album.setId(chapter.getAlbumId());
        albumMapper.updateCount(album);
        System.out.println(album + "==============updatefile");
    }

    @Override
    public void update(Chapter chapter) {

        chapterMapper.update(chapter);
    }

    @Override
    public void del(String[] id) {
        chapterMapper.del(id);
    }


}
