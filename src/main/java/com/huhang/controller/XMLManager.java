package com.huhang.controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class XMLManager {
	@Test
	public void testReadXML() throws DocumentException{
		readXML("src/controller.xml");
	}
	public ActionController readXML(String path) throws DocumentException{
		SAXReader reader=new SAXReader();
		Document document=reader.read(path);
		Element root=document.getRootElement();
		ActionController actionController=new ActionController();
		actionController.setActions(readActions(root));
		actionController.setInterceptors(readInterceptors(root));
		return actionController;
	}
	protected List<Action> readActions(Element root) {
		List<Action> actions=new LinkedList<>();
		for(Iterator i=root.elementIterator("action");i.hasNext();){//iterate through child elements of root with element name "action" 
			Element eleAction=(Element)i.next();
			Action action=new Action();
			//set name for Action
			action.setName(eleAction.selectSingleNode("name").getText());
			//set actionClass for Action
			ActionClass actionClass;
			actionClass=readActionClass(eleAction);
			action.setActionClass(actionClass);
			//set interceptorRefName
			Node interceptorRefNameNode=eleAction.selectSingleNode("interceptor-ref/name");
			if(interceptorRefNameNode!=null)
				action.setInterceptorRefName(interceptorRefNameNode.getText());
			//set resultList for Action
			List<Result> results;
			results=readResults(eleAction);
			action.setResults(results);
			//add action into List<Action> actions
			actions.add(action);
		}
		System.out.println("action0:"+actions.get(0).getName());
		System.out.println("action1:"+actions.get(1).getName());
		return actions;
	}
	protected List<Interceptor> readInterceptors(Element root){
		List<Interceptor> interceptors=new LinkedList<>();
		for(Iterator i=root.elementIterator("interceptor");i.hasNext();){//iterate through child elements of root with element name "interceptor"
			Element eleInterceptor=(Element)i.next();
			Interceptor interceptor=new Interceptor();
			interceptor.setName(eleInterceptor.selectSingleNode("name").getText());
			ActionClass actionClass=readActionClass(eleInterceptor);
			interceptor.setActionClass(actionClass);
			interceptors.add(interceptor);
		}
		return interceptors;
	}
	protected List<Result> readResults(Element eleAction) {
		// TODO Auto-generated method stub
		List<Result> resultList=new LinkedList<>();
		for(Iterator i=eleAction.elementIterator();i.hasNext();){//iterate through child elements of eleAction
			Element element =(Element)i.next();
			if(element.getName().equals("result")){//There are name, class and result inside eleAction
			Result result=new Result();
			result.setName(element.selectSingleNode("name").getText());
			//System.out.println(element.selectSingleNode("name").getText());
			result.setType(element.selectSingleNode("type").getText());
			result.setValue(element.selectSingleNode("value").getText());
			resultList.add(result);
			}
		}
		return resultList;
	}

	protected ActionClass readActionClass(Element eleAction) {
		// TODO Auto-generated method stub
		ActionClass actionClass=new ActionClass();
		actionClass.setName(eleAction.selectSingleNode("class/name").getText());
		actionClass.setMethod(eleAction.selectSingleNode("class/method").getText());
		return actionClass;
	}

}
