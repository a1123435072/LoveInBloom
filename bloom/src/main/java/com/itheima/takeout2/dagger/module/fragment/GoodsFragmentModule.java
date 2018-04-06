package com.itheima.takeout2.dagger.module.fragment;

import com.itheima.takeout2.presenter.fragment.GoodsFragmentPresenter;
import com.itheima.takeout2.ui.fragment.GoodsFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by itheima.
 */
@Module
public class GoodsFragmentModule {
    private GoodsFragment fragment;

    public GoodsFragmentModule(GoodsFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public GoodsFragmentPresenter provideGoodsFragmentPresenter(){
        return new GoodsFragmentPresenter(fragment);
    }
}
