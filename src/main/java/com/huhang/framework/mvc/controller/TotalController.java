package com.huhang.framework.mvc.controller;

import com.huhang.framework.ioc.ApplicationContext;
import com.huhang.framework.mvc.exception.NoResourceException;
import com.huhang.userlevel.entity.User;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * Created by joanna on 4/5/17.
 */
public class TotalController extends HttpServlet{

    RequestDispatcher requestDispatcher= (RequestDispatcher) ApplicationContext.INSTANCE.getBean("requestDispatcher");
    ResultDispatcher resultDispatcher= (ResultDispatcher) ApplicationContext.INSTANCE.getBean("resultDispatcher");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ControllerContext controllerContext=new ControllerContext();
        controllerContext.setRequest(request);
        controllerContext.setResponse(response);
        try {
            requestDispatcher.dispatch(controllerContext);
            resultDispatcher.dispatch(controllerContext);
        } catch (NoResourceException e) {
            e.printStackTrace();
            controllerContext.setResultDescriptor("error");
            response.sendRedirect(request.getContextPath()+ controllerContext.getResultDescriptor().getValue());
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
