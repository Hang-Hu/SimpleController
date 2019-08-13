## Description

<!-- 1. A simple controller based on Servlet and JSP.
2. Elements are provided such as Interceptor, XSLT component and XML Proccessor which is a runtime processor used for invoking Java classes at runtime based on XML data.
3. Setter injection and inversion of control support based on reflection.
 -->
1. SimpleController is a Servlet-based MVC framework that implements Inversion of Control Container, Basic Views, and Interceptor.
2. Inversion of Control Container manages objects using setter injection.
2. A Servlet will intercept all requests, construct the corresponding Action Object with Java Reflection according to the configuration file and forward the request to Action Object.
3. For the result returned by the Action, SimpleController also returns the corresponding view according to the configuration file. If it is an HTML view, it returns to browser directly; if it is an XML view, it is converted to HTML by XSLT and then returned to the browser.
4. Dynamic proxy is used to implement the interceptor LogWriter, which can log for Action Objects specified in the configuration file.

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

<!-- ## 项目说明

1.SimpleController是一个简单的基于Servlet的控制器，实现了控制反转、基本视图和拦截器。  
2.可以拦截所有请求，根据配置文件用反射构造相应Action并转发请求。  
3.对于Action返回的结果，SimpleController也根据配置文件返回相应视图：如果是HTML视图，直接返回；若是XML视图，则通过XSLT转化为HTML后再返回浏览器。  
4.使用了动态代理机制实现拦截器LogWriter，能对配置文件中指定使用这一拦截器的Action记录日志。  

 -->