package com.huhang.framework.mvc.controller;

import com.huhang.framework.mvc.exception.NoResourceException;
import com.huhang.framework.mvc.model.ResultDescriptor;
import com.huhang.framework.mvc.view.ViewGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by joanna on 4/9/17.
 */
public class ResultDispatcher {
    private ViewGenerator viewGenerator;

    public void dispatch(ControllerContext controllerContext)
            throws ServletException, IOException, NoResourceException {
        HttpServletRequest request=controllerContext.getRequest();
        HttpServletResponse response=controllerContext.getResponse();
        ResultDescriptor resultDescriptor=controllerContext.getResultDescriptor();
        if(resultDescriptor ==null){
            System.out.println("ResultDescriptor is null.");
            throw new NoResourceException("matched ResultDescriptor is null.");
        }else{
            if(resultDescriptor.getType().equals("forward")){
                String resultString= resultDescriptor.getValue();
                viewGenerator.generate(controllerContext);
                request.getRequestDispatcher(resultString.substring(0, resultString.lastIndexOf("."))+".html")
                        .forward(request, response);
                return;
            }else if(resultDescriptor.getType().equals("redirect")){
                response.sendRedirect(request.getContextPath()+ resultDescriptor.getValue());
            }else{
                request.getRequestDispatcher(resultDescriptor.getName()).forward(request, response);
            }
        }
    }
}
