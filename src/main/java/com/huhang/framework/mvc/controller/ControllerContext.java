package com.huhang.framework.mvc.controller;


import com.huhang.framework.mvc.model.ActionDescriptor;
import com.huhang.framework.mvc.model.ResultDescriptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by joanna on 4/9/17.
 */
public class ControllerContext {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ActionDescriptor actionDescriptor;
    private ResultDescriptor resultDescriptor;



    public ActionDescriptor getActionDescriptor() {
        return actionDescriptor;
    }

    public void setActionDescriptor(ActionDescriptor actionDescriptor) {
        this.actionDescriptor = actionDescriptor;
    }

    public ResultDescriptor getResultDescriptor() {
        return resultDescriptor;
    }

    public boolean setResultDescriptor(String resultString) {
        if(actionDescriptor==null){
            return false;
        }else{
            this.resultDescriptor=actionDescriptor.findMatchedResult(resultString);
            return true;
        }
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
