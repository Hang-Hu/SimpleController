package com.huhang.userlevel.service;

import com.huhang.userlevel.dao.UserDao;
import com.huhang.userlevel.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by joanna on 4/5/17.
 */
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    @Override
    public boolean doLogin(HttpServletRequest req, HttpServletResponse resp) {
        User user=new User();
        user.setName(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        if(("huhang".equals(user.getName()))&&("123".equals(user.getPassword()))){
            req.getSession().setAttribute("user", user);
            return true;
        }else{
            return false;
        }    }
}
