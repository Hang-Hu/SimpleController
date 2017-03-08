package com.huhang.model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction extends AbstractAction implements ActionInterface{

	public String doLogout(HttpServletRequest request,HttpServletResponse response){
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
