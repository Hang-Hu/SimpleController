package com.huhang.framework.mvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionInterface {
	public String execute(ControllerContext controllerContext) throws IOException;
}
