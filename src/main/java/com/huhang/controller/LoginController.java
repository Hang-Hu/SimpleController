package com.huhang.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import org.dom4j.DocumentException;

import com.huhang.model.ActionInterface;
import com.huhang.model.ActionInvocationHandler;
import com.huhang.model.SuccessXMLGenerator;
import com.huhang.model.User;
import com.huhang.xslt.FirstXSLTTransformer;

/**
 * Servlet implementation class LoginController
 */
//@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }
    @Override
    public void init() throws ServletException {
    	System.out.println("Servlet initiation.");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String URI=request.getRequestURI();
		System.out.println(URI);
		String actionString=URI.substring(URI.lastIndexOf("/")+1, URI.indexOf("."));
		System.out.println(actionString);
		if(this.getServletContext().getAttribute("actionController")==null){
			System.out.println("Get ActionController from XMLManager.");
			try {
				this.getServletContext().setAttribute("actionController", new XMLManager().readXML(getServletContext().getRealPath("WEB-INF/classes/controller.xml")));
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		ActionController actionController = (ActionController)this.getServletContext().getAttribute("actionController");
		if(actionController==null){
			System.out.println("actionController is null");
			System.exit(0);
		}
		Action matchedAction=actionController.findMatchedAction(actionString);
		if(matchedAction==null){//didn't find this action in configuration file
			System.out.println("matched Action is null.");
			request.getRequestDispatcher("/unrecognized.jsp").forward(request, response);
			return;
		}
		String resultString="";
		try {
			resultString = useProxy(request, response, actionController, matchedAction);
		} catch (IllegalArgumentException | ClassNotFoundException | InstantiationException | IllegalAccessException
				| NoSuchMethodException | SecurityException | InvocationTargetException e) {
			e.printStackTrace();
		}
		if(resultString.equals("NotFoundInterceptorInRef"))
			return;
		Result matchedResult=matchedAction.findMatchedResult(resultString);
		if(matchedResult==null){
			System.out.println("matched Result is null.");
			request.getRequestDispatcher("/noResource.jsp").forward(request, response);
		}else{
			if(matchedResult.getType().equals("forward")){
				String resultValue=matchedResult.getValue();
				String viewStr=resultValue.substring(1, resultValue.lastIndexOf("."));
				System.out.println(viewStr);
				try {
					generateView(request, viewStr);
				} catch (DocumentException | TransformerException | InterruptedException e) {
					e.printStackTrace();
				}
				request.getRequestDispatcher(viewStr+".html").forward(request, response);
				return;
			}else if(matchedResult.getType().equals("redirect")){
				response.sendRedirect(request.getContextPath()+matchedResult.getValue());
			}
		}
	}
	protected void generateView(HttpServletRequest request, String viewStr) throws DocumentException, IOException, TransformerException, InterruptedException{
		SuccessXMLGenerator generator=new SuccessXMLGenerator();
		User user=(User)request.getSession().getAttribute("user");
		generator.updateXML(getServletContext().getRealPath(viewStr+".xml"), user.getName(), user.getAge());
		//Thread.sleep(500);
		InputStream xslInput=getServletContext().getResourceAsStream("WEB-INF/classes/"+viewStr+".xsl");
		InputStream xmlInput=getServletContext().getResourceAsStream(viewStr+".xml");
		OutputStream output=new FileOutputStream(getServletContext().getRealPath(viewStr+".html"));
		FirstXSLTTransformer transformer=new FirstXSLTTransformer();
		transformer.transform(xmlInput, xslInput, output);
		output.close();
		//Thread.sleep(500);
	}
	protected String useProxy(HttpServletRequest request, HttpServletResponse response,
			ActionController actionController, Action matchedAction)
			throws ServletException, IOException, IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
		ActionInvocationHandler actionInvocationHandler;
		Class classForTarget = Class.forName(matchedAction.getActionClass().getName());
		Object actionTarget=classForTarget.newInstance();
		String interceptorName=matchedAction.getInterceptorRefName();
		if(interceptorName!=null){
			Interceptor matchedInterceptor=actionController.findMatchedInterceptor(interceptorName);
			if(matchedInterceptor==null){
				request.getRequestDispatcher("/NotFoundInterceptorInRef.jsp").forward(request, response);
				return "NotFoundInterceptorInRef";
			}else{
				actionInvocationHandler=new ActionInvocationHandler(actionTarget,matchedInterceptor,getServletContext().getRealPath("WEB-INF/classes/log.xml"),matchedAction.getName());
			}
		}else{
			actionInvocationHandler=new ActionInvocationHandler(actionTarget);
		}

		ActionInterface actionInterface=(ActionInterface)Proxy.newProxyInstance(actionTarget.getClass().getClassLoader(), actionTarget.getClass().getInterfaces(), actionInvocationHandler);
		
		String resultString=actionInterface.execute(request, response, matchedAction.getActionClass().getMethod());
		System.out.println("Result String:"+resultString);
		return resultString;
	}
	

	//now not in use, useful in E2
	private String invokeActionClassMethod(Action matchedAction,HttpServletRequest request,HttpServletResponse response) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException{
		Class[] parameterTypes=new Class[2];
		parameterTypes[0]=HttpServletRequest.class;
		parameterTypes[1]=HttpServletResponse.class;
		String resultString=null;
		Class cls = Class.forName(matchedAction.getActionClass().getName());
		Object obj=cls.newInstance();
		Method method=cls.getDeclaredMethod(matchedAction.getActionClass().getMethod(), parameterTypes);
		resultString=(String) method.invoke(obj,request,response);
		return resultString;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
