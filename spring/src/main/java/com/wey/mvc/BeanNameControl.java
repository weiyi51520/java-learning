package com.wey.mvc;

import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yale.Wei
 * @date 2018/10/9 11:10
 */
public class BeanNameControl implements HttpRequestHandler {
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("name","HttpRequestHandler");
		request.getRequestDispatcher("/WEB-INF/page/userView.jsp").forward(request,response);
	}
}
