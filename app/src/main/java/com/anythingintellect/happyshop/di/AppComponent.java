package com.anythingintellect.happyshop.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ishan.dhingra on 30/08/17.
 */
@Component(modules = BaseModule.class)
@Singleton
public interface AppComponent {

    ContextComponent plusContextModule(ContextModule contextModule);

}
