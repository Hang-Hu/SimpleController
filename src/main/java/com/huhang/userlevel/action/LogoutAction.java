package com.huhang.userlevel.action;


import com.huhang.framework.mvc.controller.ActionInterface;
import com.huhang.framework.mvc.controller.ControllerContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements ActionInterface {

	public String execute(ControllerContext controllerContext){
        HttpServletRequest request=controllerContext.getRequest();
        HttpServletResponse response=controllerContext.getResponse();
		if(request.getSession()==null)
			return "fail";
		else{
			String name=request.getParameter("userName");
			int age=Integer.parseInt(request.getParameter("userAge"));
			request.getSession().invalidate();
			return "success";
		}
	}
}
