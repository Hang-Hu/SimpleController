package com.huhang.framework.mvc.configuration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.huhang.framework.mvc.model.ActionDescriptor;
import com.huhang.framework.mvc.model.ResultDescriptor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class XMLManager {

	private Element readXML(String path){
		SAXReader reader=new SAXReader();
        Document document= null;
        try {
            document = reader.read(path);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root=document.getRootElement();
        return root;
	}
	public List<ActionDescriptor> readActions(String path) {
        Element root = readXML(path);
        List<ActionDescriptor> actionDescriptors =new LinkedList<>();
		for(Iterator i=root.elementIterator("action");i.hasNext();){//iterate through child elements of root with element name "actionDescriptor"
			Element eleAction=(Element)i.next();
			ActionDescriptor actionDescriptor =new ActionDescriptor();
			//set name for ActionDescriptor
			actionDescriptor.setName(eleAction.selectSingleNode("name").getText());
			//set resultList for ActionDescriptor
			List<ResultDescriptor> resultDescriptors;
			resultDescriptors =readResults(eleAction);
			actionDescriptor.setResultDescriptors(resultDescriptors);
			//add actionDescriptor into List<ActionDescriptor> actionDescriptors
			actionDescriptors.add(actionDescriptor);
		}
		System.out.println("action0:"+ actionDescriptors.get(0).getName());
		System.out.println("action1:"+ actionDescriptors.get(1).getName());
		return actionDescriptors;
	}

	protected List<ResultDescriptor> readResults(Element eleAction) {
		List<ResultDescriptor> resultDescriptorList =new LinkedList<>();
		for(Iterator i=eleAction.elementIterator();i.hasNext();){//iterate through child elements of eleAction
			Element element =(Element)i.next();
			if(element.getName().equals("result")){//There are name, class and resultDescriptor inside eleAction
			ResultDescriptor resultDescriptor =new ResultDescriptor();
			resultDescriptor.setName(element.selectSingleNode("name").getText());
			//System.out.println(element.selectSingleNode("name").getText());
			resultDescriptor.setType(element.selectSingleNode("type").getText());
			resultDescriptor.setValue(element.selectSingleNode("value").getText());
			resultDescriptorList.add(resultDescriptor);
			}
		}
		return resultDescriptorList;
	}


}
