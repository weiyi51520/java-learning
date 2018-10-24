package com.wey.mvc;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yale.Wei
 * @date 2018/10/9 11:14
 */
public class SimpleControl implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("userView");
		mv.addObject("name", "yale is a good man");
		return mv;
	}
}
