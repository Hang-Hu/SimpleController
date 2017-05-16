package com.huhang.framework.mvc.controller;

import com.huhang.framework.ioc.ApplicationContext;
import com.huhang.framework.mvc.configuration.ActionConfiguration;
import com.huhang.framework.mvc.exception.NoResourceException;
import com.huhang.framework.mvc.model.ActionDescriptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Proxy;

/**
 * Created by joanna on 4/9/17.
 */
public class RequestDispatcher {

    ActionConfiguration actionConfiguration;

    public void dispatch(ControllerContext controllerContext)
            throws ServletException, IOException, NoResourceException {
        HttpServletRequest request=controllerContext.getRequest();
        HttpServletResponse response=controllerContext.getResponse();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String URI=request.getRequestURI();
        System.out.println(URI);
        String actionString=URI.substring(URI.lastIndexOf("/")+1, URI.indexOf("."));
        System.out.println(actionString);
        ActionDescriptor matchedActionDescriptor = actionConfiguration.findMatchedAction(actionString);
        controllerContext.setActionDescriptor(matchedActionDescriptor);
        String resultString="";
        if(matchedActionDescriptor ==null){//didn't find this action in configuration file
            throw new NoResourceException("matched ActionDescriptor is null.");
        }else {
            resultString = useProxy(controllerContext);
        }
        if(controllerContext.getResultDescriptor()==null)
            controllerContext.setResultDescriptor(resultString);
    }


    protected String useProxy(ControllerContext controllerContext){
        ActionInterface actionInterface= (ActionInterface) ApplicationContext.INSTANCE.getBean(
                controllerContext.getActionDescriptor().getName());
        String resultString= "";
        try {
            resultString = actionInterface.execute(controllerContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ResultDescriptor String:"+resultString);
        return resultString;
    }

}
