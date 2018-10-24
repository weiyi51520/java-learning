package com.wey.ioc;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Yale.Wei
 * @date 2018/9/23 19:44
 */
public class SpringBean {
	private String name;
	private int sex;
	private RefBean ref;

	public SpringBean() {
	}

	public SpringBean(RefBean ref) {
		this.ref = ref;
	}

	public SpringBean(String name, int sex) {
		this.name = name;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public static SpringBean createBean(String name){
		if (StringUtils.equalsIgnoreCase(name,"Yale.Wei")){
			return new SpringBean(name,1);
		}else {
			throw new IllegalArgumentException("Unexpected argument name when create SpringBean!");
		}
	}

	public RefBean getRef() {
		return ref;
	}

	public void setRef(RefBean ref) {
		this.ref = ref;
	}
}
