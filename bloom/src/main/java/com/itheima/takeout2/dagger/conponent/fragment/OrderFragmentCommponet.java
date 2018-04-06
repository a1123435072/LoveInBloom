package com.itheima.takeout2.dagger.conponent.fragment;

import com.itheima.takeout2.dagger.module.fragment.OrderFragmentModule;
import com.itheima.takeout2.ui.fragment.OrderFragment;

import dagger.Component;

/**
 * Created by itheima.
 */
@Component(modules = OrderFragmentModule.class)
public interface OrderFragmentCommponet {
    void in(OrderFragment fragment);
}
