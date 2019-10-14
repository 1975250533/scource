package com.baizhi.services;

import com.baizhi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserMapper userMapper;


    @Override
    public List< String > queryAll() {
        List< String > province = userMapper.queryAll();
        return province;
    }

    @Override
    public Integer count(String province) {
        Integer count = userMapper.count(province);
        return count;
    }

    @Override
    public List< Integer > query() {
        List< Integer > query = userMapper.query();
        return query;
    }
}
