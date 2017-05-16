package com.huhang.framework.mvc.view;

import com.huhang.framework.mvc.controller.ControllerContext;
import com.huhang.framework.mvc.xslt.XsltTransformer;
import com.huhang.framework.mvc.xslt.XmlGenerator;
import com.huhang.userlevel.entity.User;

import java.io.*;

/**
 * Created by joanna on 4/11/17.
 */
public class XmlViewGenerator implements ViewGenerator{
    XmlGenerator xmlGenerator;
    XsltTransformer xsltTransformer;

    @Override
    public void generate(ControllerContext controllerContext) {
        String resultString=controllerContext.getResultDescriptor().getValue();
        String viewStr=resultString.substring(0, resultString.lastIndexOf("."));
        User user=(User)controllerContext.getRequest().getSession().getAttribute("user");
        String path=Thread.currentThread().getContextClassLoader().getResource("").getPath();
        try {
            xmlGenerator.updateXML(path+viewStr+".xml", user.getName(), user.getAge());
            InputStream xslInput=new FileInputStream(path+viewStr+".xsl");
            InputStream xmlInput=new FileInputStream(path+viewStr+".xml");
            System.out.println(path.substring(0, path.indexOf("WEB-INF"))+viewStr+".html");
            OutputStream output=new FileOutputStream(path.substring(0, path.indexOf("WEB-INF"))+viewStr+".html");

            xsltTransformer.transform(xmlInput, xslInput, output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
