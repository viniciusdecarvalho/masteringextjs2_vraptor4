package br.com.vinniccius.masteringextjs.extjs;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({METHOD})
@Documented
@Retention(RUNTIME)
public @interface ExtJSConsumes {

	String root() default "data";
	
}
