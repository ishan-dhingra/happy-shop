package com.anythingintellect.happyshop.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ishan.dhingra on 30/08/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface PerContext {

}
