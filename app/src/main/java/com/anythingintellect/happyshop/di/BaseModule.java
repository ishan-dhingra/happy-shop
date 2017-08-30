package com.anythingintellect.happyshop.di;

import dagger.Module;

/**
 * Created by ishan.dhingra on 30/08/17.
 */
@Module(includes = {NetworkModule.class, PersistenceModule.class})
public class BaseModule {

}
