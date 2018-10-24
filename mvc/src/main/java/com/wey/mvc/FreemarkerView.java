package com.wey.mvc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yale.Wei
 * @date 2018/10/9 17:25
 */
public class FreemarkerView {
	private String ftlPath;
	private Map<String,Object> models = new HashMap<>();

	public FreemarkerView(String ftlPath) {
		this.ftlPath = ftlPath;
	}

	public FreemarkerView(String ftlPath, Map<String, Object> models) {
		this.ftlPath = ftlPath;
		this.models = models;
	}

	public String getFtlPath() {
		return ftlPath;
	}

	public void setFtlPath(String ftlPath) {
		this.ftlPath = ftlPath;
	}

	public Map<String, Object> getModels() {
		return models;
	}

	public void setModels(Map<String, Object> models) {
		this.models = models;
	}

	public void addModel(String key,Object model){
		models.put(key, model);
	}

	public void removeModel(String key){
		models.remove(key);
	}
}
