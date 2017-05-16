package com.huhang.userlevel.action;

import com.huhang.framework.mvc.controller.ActionInterface;
import com.huhang.framework.mvc.controller.ControllerContext;
import com.huhang.userlevel.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class LoginAction implements ActionInterface {
		
	
	public String execute(ControllerContext controllerContext){
        HttpServletRequest request=controllerContext.getRequest();
        HttpServletResponse response=controllerContext.getResponse();
		if(request.getSession().getAttribute("user")!=null){
			request.setAttribute("msg", "You have already logged in.");
			return "success";
		}
		User user=new User();
		user.setName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setAge(22);
		if(user.validate()==true){
			request.getSession().setAttribute("user", user);
			return "success";
		}
		else
			return "fail";
	}

}
