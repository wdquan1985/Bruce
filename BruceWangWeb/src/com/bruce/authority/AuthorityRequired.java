package com.bruce.authority;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorityRequired {
	AuthorityType[] authorityTypes();
	ResultTypeEnum resultType() default ResultTypeEnum.page;
}
