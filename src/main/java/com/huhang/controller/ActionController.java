package com.huhang.controller;

import java.util.Iterator;
import java.util.List;

public class ActionController {
	private List<Interceptor> interceptors;
	private List<Action> actions;
	
	public List<Interceptor> getInterceptors() {
		return interceptors;
	}
	public void setInterceptors(List<Interceptor> interceptors) {
		this.interceptors = interceptors;
	}
	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	public Action findMatchedAction(String actionString){
		for(Iterator i=actions.listIterator();i.hasNext();){
			Action action=(Action)i.next();
			if(actionString.equals(action.getName()))//match this action
				return action;
		}
		//if not find any matched Action
		return null;
	}
	public Interceptor findMatchedInterceptor(String interceptorName){
		for(Iterator i=interceptors.listIterator();i.hasNext();){
			Interceptor interceptor=(Interceptor) i.next();
			if(interceptor.getName().equals(interceptorName)){
				return interceptor;
			}
		}
		return null;
	}
}
