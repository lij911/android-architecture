package com.lijing.dev.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 用于 api
 *
 * @author lijing
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiScope {
}
