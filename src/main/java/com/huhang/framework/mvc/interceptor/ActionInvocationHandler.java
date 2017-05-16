package com.huhang.framework.mvc.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.huhang.framework.mvc.controller.ControllerContext;
import com.huhang.framework.mvc.exception.NoResourceException;

public class ActionInvocationHandler implements InvocationHandler {
	protected Object target;
    private HandlerInterceptor handlerInterceptor;

	public ActionInvocationHandler(Object target,
                                   Object interceptor) {
		this.target=target;
        handlerInterceptor= (HandlerInterceptor)interceptor ;
	}

    @Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ControllerContext controllerContext=(ControllerContext) args[0];
        if(controllerContext==null){
            throw new NoResourceException("You have to set ControllerContext before calling invoke");
        }
        handlerInterceptor.preHandle(controllerContext.getRequest(),
                controllerContext.getResponse(), controllerContext.getActionDescriptor());

        String resultString=(String) method.invoke(target, args);
        controllerContext.setResultDescriptor(resultString);
        handlerInterceptor.postHandle(controllerContext.getRequest(),
                controllerContext.getResponse(),
                controllerContext.getResultDescriptor());
		return resultString;
	}

}
