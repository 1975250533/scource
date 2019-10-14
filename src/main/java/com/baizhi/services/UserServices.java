package com.baizhi.services;

import java.util.List;

public interface UserServices {
    public List< String > queryAll();

    public Integer count(String province);

    public List< Integer > query();
}
