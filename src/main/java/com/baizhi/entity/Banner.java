package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner {
    @Excel(name = "主键", needMerge = true)
    private String id;
    @Excel(name = "标题", needMerge = true)
    private String title;
    @Excel(name = "图片", type = 2, width = 40, height = 30)
    private String imgPath;
    @Excel(name = "状态", replace = {"展示_y", "不展示_n"}, needMerge = true)
    private String status;
    @Excel(name = "创建时间", format = "yyyy-MM-dd", width = 20, needMerge = true)
    private Date createDate;
    @Excel(name = "描述", needMerge = true)
    private String desc;

}
