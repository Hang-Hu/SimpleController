package com.huhang.framework.mvc.model;

import java.util.Iterator;
import java.util.List;

public class ActionDescriptor {
	private String name;
	private List<ResultDescriptor> resultDescriptors;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ResultDescriptor> getResultDescriptors() {
		return resultDescriptors;
	}
	public void setResultDescriptors(List<ResultDescriptor> resultDescriptors) {
		this.resultDescriptors = resultDescriptors;
	}
	public ResultDescriptor findMatchedResult(String resultString){
		for(Iterator i = resultDescriptors.listIterator(); i.hasNext();){
			ResultDescriptor resultDescriptor =(ResultDescriptor)i.next();
			if(resultString.equals(resultDescriptor.getName())){
				return resultDescriptor;
			}
		}
		//if not find any matched ResultDescriptor
		return null;
	}
}
