package com.huhang.userlevel.interceptor;

import com.huhang.userlevel.log.LogWriter;
import com.huhang.framework.mvc.interceptor.HandlerInterceptor;
import com.huhang.framework.mvc.model.ActionDescriptor;
import com.huhang.framework.mvc.model.ResultDescriptor;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by joanna on 4/9/17.
 */
public class LogInterceptor implements HandlerInterceptor {
    ActionLog actionLog=new ActionLog();
    LogWriter logWriter;

    @Override
    public void preHandle(HttpServletRequest request, HttpServletResponse response, ActionDescriptor actionDescriptor) {
        actionLog.setStartTime(new Date());
        actionLog.setName(actionDescriptor.getName());

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, ResultDescriptor resultDescriptor) {
        actionLog.setEndTime(new Date());
        actionLog.setResult(resultDescriptor.getName());
        logWriter.log(actionLog);
    }
}
