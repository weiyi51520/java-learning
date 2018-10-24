package com.wey.mvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Yale.Wei
 * @date 2018/10/10 9:55
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface MvcMapping {

	String value();

	String contentType() default "JSON";
}
