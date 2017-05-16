## Description

1. A simple controller based on Servlet and JSP.
2. Elements are provided such as Interceptor, XSLT component and XML Proccessor which is a runtime processor used for invoking Java classes at runtime based on XML data.
3. Setter injection and inversion of control support based on reflection.

## Ideas about IoC
All classes managed by IoC container except Sevlet, TotalController specifically.

First do it from `getBean()`, then change it to autowired now that their creation is managed by IoC container.
```
interface XXInterface{
}
class XXImpl{
}
class Client{
    private XXInterface xXInterface;
    //injected by IoC container, 
    //the type of impl dependends on the IoC configuration file context.xml
}

<bean id="client" class="">
    <property name="xXInterface" ref"xXImpl"/>
</bean>
<bean id="xXInterImpl" class=""></bean>
```

## 项目说明

1.SimpleController是一个简单的基于Servlet的控制器，实现了控制反转、基本视图和拦截器。  
2.可以拦截所有请求，根据配置文件用反射构造相应Action并转发请求。  
3.对于Action返回的结果，SimpleController也根据配置文件返回相应视图：如果是HTML视图，直接返回；若是XML视图，则通过XSLT转化为HTML后再返回浏览器。  
4.使用了动态代理机制实现拦截器LogWriter，能对配置文件中指定使用这一拦截器的Action记录日志。  

