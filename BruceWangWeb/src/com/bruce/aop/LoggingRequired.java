package com.bruce.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//创建了一个新的annotation.
@Target({ElementType.METHOD})//can add on a method or a class
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggingRequired {
}
