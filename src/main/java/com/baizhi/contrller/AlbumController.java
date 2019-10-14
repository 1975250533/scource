package com.baizhi.contrller;

import com.baizhi.entity.Album;
import com.baizhi.services.AlbumServices;
import com.baizhi.services.ChapterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumServices albumServices;
    @Autowired
    private ChapterServices chapterServices;

    @RequestMapping("findAllAlbum")
    public Map< String, Object > findAllAlbum(Integer page, Integer rows) {
        Map< String, Object > allAlbum = albumServices.findAllAlbum(page, rows);
        return allAlbum;
    }


    @RequestMapping("edit")
    public String edit(Album album, String oper, String[] id) {
        if (oper.equals("add")) {
            String s = albumServices.add(album);
            return s;
        }
        if ("edit".equals(oper)) {
            System.out.println("controller======");
            albumServices.updateMsg(album);
        }
        if ("del".equals(oper)) {
            albumServices.del(id);
        }

        return null;
    }


    @RequestMapping("upload")
    public void upload(MultipartFile cover, String bannerId, HttpSession session) {
        System.out.println(cover);
        //获取图片存储的位置
        String realPath = session.getServletContext().getRealPath("/img");

        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = cover.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;

        try {
            cover.transferTo(new File(realPath, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //修改图片的路径
        Album album = new Album();
        album.setCover(newFileName);
        album.setId(bannerId);
        albumServices.updateUrl(album);

        System.out.println(cover + "++++++++++++++============");
    }
}
