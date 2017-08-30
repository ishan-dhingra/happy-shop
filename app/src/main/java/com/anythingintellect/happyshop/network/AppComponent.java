package com.anythingintellect.happyshop.network;

import dagger.Component;

/**
 * Created by ishan.dhingra on 30/08/17.
 */
@Component(modules = BaseModule.class)
public interface AppComponent {

    FragmentComponent plusFragmentModule(FragmentModule fragmentModule);

}
