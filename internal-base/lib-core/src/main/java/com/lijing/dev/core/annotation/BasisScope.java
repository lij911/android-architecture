package com.lijing.dev.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 提供底层各个功能件
 *
 * @author lijing
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface BasisScope {
}
