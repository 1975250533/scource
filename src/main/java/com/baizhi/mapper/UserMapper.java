package com.baizhi.mapper;

import java.util.List;


public interface UserMapper {


    public List< String > queryAll();

    public Integer count(String province);

    public List< Integer > query();


}
