package com.huhang.userlevel.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by joanna on 4/5/17.
 */
public interface UserService {
    boolean doLogin(HttpServletRequest req, HttpServletResponse resp);
}
