package com.baizhi.contrller;

import com.baizhi.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    AdminServices adminServices;

    @RequestMapping("/login")
    @ResponseBody
    public Map< String, Object > login(String username, String password, String enCode, HttpSession session) {
        Map< String, Object > map = adminServices.find(username, password, enCode, session);
        System.out.println(map);
        return map;
    }
}
