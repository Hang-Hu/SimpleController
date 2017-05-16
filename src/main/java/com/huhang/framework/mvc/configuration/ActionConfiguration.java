package com.huhang.framework.mvc.configuration;



import com.huhang.framework.mvc.model.ActionDescriptor;

import java.util.Iterator;
import java.util.List;

public class ActionConfiguration {
	private List<ActionDescriptor> actionDescriptors;

    public ActionConfiguration() {
        String path=Thread.currentThread().getContextClassLoader().getResource("controller.xml").getPath();
        XMLManager xmlManager = new XMLManager();
        //interceptorDescriptors=xmlManager.readInterceptors(path);
        actionDescriptors=xmlManager.readActions(path);
    }



	public List<ActionDescriptor> getActionDescriptors() {
		return actionDescriptors;
	}
	public void setActionDescriptors(List<ActionDescriptor> actionDescriptors) {
		this.actionDescriptors = actionDescriptors;
	}
	public ActionDescriptor findMatchedAction(String actionString){
		for(Iterator i = actionDescriptors.listIterator(); i.hasNext();){
			ActionDescriptor actionDescriptor =(ActionDescriptor)i.next();
			if(actionString.equals(actionDescriptor.getName()))//match this actionDescriptor
				return actionDescriptor;
		}
		//if not find any matched ActionDescriptor
		return null;
	}

}
