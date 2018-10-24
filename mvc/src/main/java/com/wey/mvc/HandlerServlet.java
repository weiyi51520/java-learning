package com.wey.mvc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Yale.Wei
 * @date 2018/10/10 9:33
 */
public class HandlerServlet extends HttpServlet{

	private WebApplicationContext context;
	private MvcBeanFactory beanFactory;
	final ParameterNameDiscoverer parameterUtil = new LocalVariableTableParameterNameDiscoverer();
	private Configuration fmConfig;

	@Override
	public void init() throws ServletException {
		context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		beanFactory = new MvcBeanFactory(context);
		Configuration config = context.getBean(Configuration.class);
		if (config == null){
			config = new Configuration(Configuration.VERSION_2_3_19);
			config.setDefaultEncoding("UTF-8");
			config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			try {
				config.setDirectoryForTemplateLoading(new File(getServletContext().getRealPath("/ftl/")));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		this.fmConfig = config;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandler(req,resp);
	}

	public void doHandler(HttpServletRequest req, HttpServletResponse resp){
		String url = req.getServletPath();
		if (url.equals("/favicon.ico")){
			return;
		}
		MvcBeanFactory.MvcBean mvcBean = beanFactory.getMvcBean(url);
		if (mvcBean == null){
			throw new IllegalArgumentException(String.format("not found %s mapping",url));
		}
		Object[] args = buildParams(mvcBean, req, resp);
		try {
			Object result = mvcBean.run(args);
			processResult(result,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processResult(Object result, HttpServletResponse resp) throws IOException, TemplateException {
		if ( result instanceof FreemarkerView){
			FreemarkerView view = (FreemarkerView) result;
			Template temp = fmConfig.getTemplate(view.getFtlPath());
			resp.setContentType("text/html; charset=utf-8");
			resp.setCharacterEncoding("utf-8");
			resp.setStatus(200);
			temp.process(view.getModels(), resp.getWriter());
		}
	}

	public Object[] buildParams(MvcBeanFactory.MvcBean mvcBean, HttpServletRequest req, HttpServletResponse resp){
		Method method = mvcBean.getTargetMethod();
		List<String> paramNames = Arrays.asList(parameterUtil.getParameterNames(method));
		Class<?>[] paramTypes = method.getParameterTypes();
		Object[] args = new Object[paramTypes.length];
		for (int i = 0; i < paramNames.size(); i++) {
			if (paramTypes[i].isAssignableFrom(HttpServletRequest.class)){
				args[i] = req;
			}else if (paramTypes[i].isAssignableFrom(HttpServletResponse.class)){
				args[i] = resp;
			}else {
				if (req.getParameter(paramNames.get(i)) == null){
					args[i] = null;
				}else {
					args[i] = convert(req.getParameter(paramNames.get(i)),paramTypes[i]);
				}
			}
		}
		return args;
	}

	public <T> T convert(String val,Class<T> targetClass){
		Object result = null;
		if (val==null){
			return null;
		} else if (Integer.class.equals(targetClass)){
			result = Integer.parseInt(val.toString());
		}else if (Long.class.equals(targetClass)){
			result = Long.parseLong(val.toString());
		}else if (Date.class.equals(targetClass)){
			try {
				result = new SimpleDateFormat("").parse(val);
			} catch (ParseException e) {
				throw new IllegalArgumentException("date format Illegal must be 'yyyy-MM-dd HH:mm:ss'");
			}
		}else if (String.class.equals(targetClass)){
			result = val;
		}else {
			System.err.println(String.format("not suport param type %s", targetClass.getName()));
		}
		return (T) result;
	}
}