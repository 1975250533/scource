package com.baizhi.services;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface AdminServices {
    public Map< String, Object > find(String username, String password, String enCode, HttpSession session);

}
