package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private String id;
    private String title;//标题
    private String cover;//封面路径
    private String score;//评分
    private String author;
    private String beam;//播音者
    private Integer count;//集数
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;//发布时间
    private String content;//jianjie
    private String status;

}
