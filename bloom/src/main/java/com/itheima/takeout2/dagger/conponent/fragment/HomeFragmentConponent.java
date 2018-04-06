package com.itheima.takeout2.dagger.conponent.fragment;

import com.itheima.takeout2.dagger.module.fragment.HomeFragmentModule;
import com.itheima.takeout2.ui.fragment.HomeFragment;

import dagger.Component;

/**
 * 将创建好的业务对象设置给目标
 */
@Component(modules = HomeFragmentModule.class)
public interface HomeFragmentConponent {
    void in(HomeFragment fragment);
}
