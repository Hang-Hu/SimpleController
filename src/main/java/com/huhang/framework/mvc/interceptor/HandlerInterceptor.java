package com.huhang.framework.mvc.interceptor;


import com.huhang.framework.mvc.model.ActionDescriptor;
import com.huhang.framework.mvc.model.ResultDescriptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by joanna on 4/9/17.
 */
public interface HandlerInterceptor {
    public void preHandle(HttpServletRequest request, HttpServletResponse response, ActionDescriptor actionDescriptor);

    public void postHandle(HttpServletRequest request, HttpServletResponse response, ResultDescriptor resultDescriptor);
}