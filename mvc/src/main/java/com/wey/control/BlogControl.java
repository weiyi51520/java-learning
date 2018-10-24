package com.wey.control;

import com.wey.mvc.FreemarkerView;
import com.wey.mvc.MvcMapping;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yale.Wei
 * @date 2018/10/10 14:33
 */
@Controller
public class BlogControl {

	List<BlogDoc> docs = new ArrayList<>();

	@MvcMapping("/edit")
	public FreemarkerView openEditPage(String user){
		FreemarkerView view = new FreemarkerView("edit.ftl");
		view.addModel("authorName",user);
		view.addModel("user",user);
		return view;
	}

	@MvcMapping("/list")
	public FreemarkerView openDocList(String author){
		List<BlogDoc> result = new ArrayList<>();
		if (author != null){
			for (BlogDoc doc : docs) {
				if (author.equals(doc.getAuthor())){
					result.add(doc);
				}
			}
		}else {
			result.addAll(docs);
		}
		FreemarkerView view = new FreemarkerView("docList.ftl");
		view.addModel("authorName",author);
		view.addModel("docs",result);
		return view;
	}

	public void openEditPage(String title, String author, String content, HttpServletResponse res){
		BlogDoc doc = new BlogDoc(title,author,content,new Date());
		docs.add(doc);
		try {
			res.sendRedirect("/list");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


}
