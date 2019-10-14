package com.baizhi.services;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumServices {
    public Map< String, Object > findAllAlbum(Integer page, Integer rows);

    public String add(Album album);

    public void updateUrl(Album album);

    public void updateMsg(Album album);

    public void del(String[] id);

}
