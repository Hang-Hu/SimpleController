package com.huhang.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.huhang.controller.Interceptor;

public class ActionInvocationHandler implements InvocationHandler {
	protected Object target;
	protected Interceptor interceptor;
	protected String path;
	protected String actionName;
	public ActionInvocationHandler(Object target,Interceptor interceptor,String path,String actionName) {
		this.target=target;
		this.interceptor=interceptor;
		this.path=path;
		System.out.println(path);
		this.actionName=actionName;
	}
	public ActionInvocationHandler(Object target) {
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		ActionLog actionLog=new ActionLog();
		actionLog.setStartTime(new Date());
		String resultString=(String) method.invoke(target, args);
		actionLog.setEndTime(new Date());
		actionLog.setResult(resultString);
		if(interceptor!=null){
			actionLog.setName(actionName);
			invokeInterceptorMethod(interceptor, path,actionLog);
		}
		return resultString;
	}
	protected void invokeInterceptorMethod(Interceptor interceptor,String path,ActionLog actionLog) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class[] parameterTypesForMethod=new Class[1];
		parameterTypesForMethod[0]=String.class;
		Class[] parameterTypesForConstructor=new Class[1];
		parameterTypesForConstructor[0]=ActionLog.class;
		Class cls=Class.forName(interceptor.getActionClass().getName());
		Constructor constructor=cls.getConstructor(parameterTypesForConstructor);
		Object obj=constructor.newInstance(actionLog);
		Method method=cls.getDeclaredMethod(interceptor.getActionClass().getMethod(), parameterTypesForMethod);
		method.invoke(obj, path);
	}

}
