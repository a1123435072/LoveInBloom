package com.itheima.takeout2.dagger.conponent.fragment;

import com.itheima.takeout2.dagger.module.fragment.GoodsFragmentModule;
import com.itheima.takeout2.ui.fragment.GoodsFragment;

import dagger.Component;

/**
 * Created by itheima.
 */
@Component(modules = GoodsFragmentModule.class)
public interface GoodsFragmentConponent {
    void in(GoodsFragment fragment);
}
