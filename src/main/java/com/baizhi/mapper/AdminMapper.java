package com.baizhi.mapper;

import com.baizhi.entity.Admin;


public interface AdminMapper {
    //登录
    public Admin find(String username);

}
