package com.huhang.model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class LoginAction extends AbstractAction implements ActionInterface {
		
	
	public String doLogin(HttpServletRequest request, HttpServletResponse response){
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
