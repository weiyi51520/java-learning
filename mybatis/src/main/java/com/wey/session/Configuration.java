package com.wey.session;

import com.wey.binding.MapperMethod;
import com.wey.binding.MapperProxyFactory;
import com.wey.binding.MapperRegistry;
import com.wey.mapping.MappedStatement;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yale.Wei
 * @date 2018/9/14 18:05
 * 加载mybatis-config.xml全局配置 保存在pojo中
 */
public class Configuration {
	private InputStream inputStream;

	MapperRegistry mapperRegistry = new MapperRegistry(this);

	Map<String,MappedStatement> mappedStatements = new HashMap<>();
	/**
	 * 通过Dom4j读取mybatis-config.xml配置文件
	 */
	public void loanConfigurations() throws IOException {
		try {
			Document document = new SAXReader().read(inputStream);
			Element root = document.getRootElement();
			List<Element> mappers = root.element("mappers").elements("mapper");
			for (Element mapper : mappers) {
				if (mapper.attribute("resource")!=null){
					mapperRegistry.setKonwMappers(loadXMLConfiguration(mapper.attribute("resource").getText()));
				}
			}
		} catch (DocumentException e) {
			System.out.println("com.wey.session.Configuration.loanConfigurations===读取配置文件错误===");
		} finally {
			inputStream.close();
		}
	}

	/**
	 * 通过Dom4j读取slqMapper.xml文件
	 * @param resource
	 * @return
	 */
	private Map<Class<?>, MapperProxyFactory<?>> loadXMLConfiguration(String resource) throws DocumentException, IOException {
		Map<Class<?>, MapperProxyFactory<?>> map = new HashMap<>();
		InputStream is =null;

		try {
			is = this.getClass().getClassLoader().getResourceAsStream(resource);
			Element root= new SAXReader().read(is).getRootElement();
			if (root.getName().equalsIgnoreCase("mapper")){
				String namespace = root.attribute("namespace").getText();
				List<Element> selects = root.elements("select");
				for (Element select : selects) {
					map.put(Class.forName(namespace),new MapperProxyFactory(Class.forName(namespace)));
					String id = select.attributeValue("id");
					mappedStatements.put(Class.forName(namespace)+"."+id,new MappedStatement(namespace+"."+id,select.getText()));
				}

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			is.close();
		}
		return map;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public MapperRegistry getMapperRegistry() {
		return mapperRegistry;
	}

	public void setMapperRegistry(MapperRegistry mapperRegistry) {
		this.mapperRegistry = mapperRegistry;
	}

	public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
		return this.mapperRegistry.getMapper(type, sqlSession);
	}

	public Map<String, MappedStatement> getMappedStatements() {
		return mappedStatements;
	}

	public void setMappedStatements(Map<String, MappedStatement> mappedStatements) {
		this.mappedStatements = mappedStatements;
	}
}
