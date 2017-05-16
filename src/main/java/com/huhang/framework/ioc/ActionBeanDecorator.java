package com.huhang.framework.ioc;

import com.huhang.framework.mvc.controller.ActionInterface;
import com.huhang.framework.mvc.controller.ControllerContext;
import com.huhang.framework.mvc.interceptor.ActionInvocationHandler;

import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * Created by joanna on 4/11/17.
 */
class ActionBeanDecorator implements ActionInterface{
    private Object DecoreatedBean;

    public ActionBeanDecorator(Object bean, Object interceptor) {
        ActionInvocationHandler actionInvocationHandler= new ActionInvocationHandler(bean, interceptor);
        Object proxyInstance = Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(),
                actionInvocationHandler);
        DecoreatedBean=proxyInstance;
    }

    @Override
    public String execute(ControllerContext controllerContext) throws IOException {
        String resultString = ((ActionInterface) DecoreatedBean).execute(controllerContext);
        return resultString;
    }
}
