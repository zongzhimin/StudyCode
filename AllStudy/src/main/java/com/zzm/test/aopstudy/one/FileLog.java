package com.zzm.test.aopstudy.one;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target(ElementType.METHOD)
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
public @interface FileLog {
	
	String value() default "日志记录开始";
}


