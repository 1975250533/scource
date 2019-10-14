package com.baizhi.services;

import com.baizhi.entity.Banner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BannerServices {
    public Map< String, Object > queryAll(Integer page, Integer rows);

    public String add(Banner banner);

    public void updateUrl(Banner banner);

    public void updateStatus(Banner banner);

    public void del(String[] id);


    public List< Banner > query(HttpServletResponse response) throws IOException;
}
