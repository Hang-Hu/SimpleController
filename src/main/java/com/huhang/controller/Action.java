package com.huhang.controller;

import java.util.Iterator;
import java.util.List;

public class Action {
	private String name;
	private ActionClass actionClass;
	private String interceptorRefName;
	private List<Result> results;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ActionClass getActionClass() {
		return actionClass;
	}
	public void setActionClass(ActionClass actionClass) {
		this.actionClass = actionClass;
	}
	public List<Result> getResults() {
		return results;
	}
	public void setResults(List<Result> results) {
		this.results = results;
	}
	public String getInterceptorRefName() {
		return interceptorRefName;
	}
	public void setInterceptorRefName(String interceptorRefName) {
		this.interceptorRefName = interceptorRefName;
	}
	public Result findMatchedResult(String resultString){
		for(Iterator i=results.listIterator();i.hasNext();){
			Result result=(Result)i.next();
			if(resultString.equals(result.getName())){
				return result;
			}
		}
		//if not find any matched Result
		return null;
	}
}
