package com.huhang.model;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionInterface {
	public String execute(HttpServletRequest request, HttpServletResponse response,String method) throws IOException;
}
