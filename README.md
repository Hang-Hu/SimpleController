1.SimpleController是一个简单的基于Servlet的控制器，实现了控制反转、基本视图和拦截器。  
2.可以拦截所有请求，根据配置文件用反射构造相应Action并转发请求。  
3.对于Action返回的结果，SimpleController也根据配置文件返回相应视图：如果是HTML视图，直接返回；若是XML视图，则通过XSLT转化为HTML后再返回浏览器。  
4.使用了动态代理机制实现拦截器LogWriter，能对配置文件中指定使用这一拦截器的Action记录日志。  
