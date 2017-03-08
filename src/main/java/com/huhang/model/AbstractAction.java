package com.huhang.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractAction {
	public String execute(HttpServletRequest request, HttpServletResponse response,String methodString) throws IOException{
		Class[] parameterTypes=new Class[2];
		parameterTypes[0]=HttpServletRequest.class;
		parameterTypes[1]=HttpServletResponse.class;
		String result=null;
		try {
			Method method=this.getClass().getDeclaredMethod(methodString, parameterTypes);
			result=(String) method.invoke(this, request,response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

}
