package com.anythingintellect.happyshop.di;

import dagger.Subcomponent;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

@Subcomponent(modules = FragmentModule.class)
@PerFragment
public interface FragmentComponent {



}