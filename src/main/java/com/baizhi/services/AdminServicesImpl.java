package com.baizhi.services;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminServicesImpl implements AdminServices {
    @Autowired
    AdminMapper adminMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map< String, Object > find(String username, String password, String enCode, HttpSession session) {
        Map< String, Object > map = new HashMap<>();


        String validationCode = (String) session.getAttribute("validationCode");
        if (enCode.equals(validationCode)) {
            Admin admin = adminMapper.find(username);
            if (admin != null) {
                if (password.equals(admin.getPassword())) {
                    map.put("msg", "ok");
                    return map;
                } else {
                    map.put("msg", "密码不正确");
                    return map;
                }
            } else {
                map.put("msg", "用户不存在");
                return map;
            }
        } else {
            map.put("msg", "验证码错误");
            return map;

        }
    }
}

