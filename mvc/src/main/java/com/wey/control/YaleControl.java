package com.wey.control;

import com.wey.mvc.FreemarkerView;
import com.wey.mvc.MvcMapping;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yale.Wei
 * @date 2018/10/10 14:53
 */
@Controller
public class YaleControl {

	@MvcMapping("/yale.do")
	public FreemarkerView openYalePage(String name){
		FreemarkerView view = new FreemarkerView("yale.ftl");
		view.addModel("name",name);
		return view;
	}

	@MvcMapping("/hello.do")
	public FreemarkerView open(String name, BlogDoc doc, HttpServletRequest request, HttpServletResponse resp){
		FreemarkerView view = new FreemarkerView("hello.ftl");
		view.addModel("name",name);
		return view;
	}
}
