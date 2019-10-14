package com.baizhi.contrller;


import com.baizhi.entity.Banner;
import com.baizhi.services.BannerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    BannerServices bannerServices;

    @RequestMapping("edit")
    public String edit(Banner banner, String oper, String[] id) {
        if (oper.equals("add")) {
            String s = bannerServices.add(banner);
            return s;
        }
        if ("edit".equals(oper)) {
            bannerServices.updateStatus(banner);
        }
        if ("del".equals(oper)) {
            bannerServices.del(id);
        }

        return null;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile imgPath, String bannerId, HttpSession session) {
        //获取图片存储的位置
        String realPath = session.getServletContext().getRealPath("/img");

        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = imgPath.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;

        try {
            imgPath.transferTo(new File(realPath, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //修改图片的路径
        Banner banner = new Banner();
        banner.setImgPath(newFileName);
        banner.setId(bannerId);
        bannerServices.updateUrl(banner);

    }

    @RequestMapping("queryAll")
    public Map< String, Object > queryAll(Integer page, Integer rows) {
        Map< String, Object > map = bannerServices.queryAll(page, rows);
        return map;
    }

    @RequestMapping("excel")
    public void excel(HttpServletResponse response) throws IOException {

        List< Banner > query = bannerServices.query(response);


    }
}
