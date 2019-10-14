package com.baizhi.contrller;

import com.baizhi.entity.EchartsMap;
import com.baizhi.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @RequestMapping("all")
    public List< EchartsMap > queryAll() {
        ArrayList< EchartsMap > maps = new ArrayList<>();
        List< String > strings = userServices.queryAll();
        for (String string : strings) {
            Integer count = userServices.count(string);
            EchartsMap echartsMap1 = new EchartsMap(string, count);
            maps.add(echartsMap1);
        }
        return maps;

    }


    @RequestMapping("query")
    public List< Integer > query() {
        List< Integer > query1 = userServices.query();
        return query1;
    }
}
