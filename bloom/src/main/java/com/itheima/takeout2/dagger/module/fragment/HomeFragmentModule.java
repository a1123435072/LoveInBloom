package com.itheima.takeout2.dagger.module.fragment;

import com.itheima.takeout2.presenter.fragment.HomeFragmentPresenter;
import com.itheima.takeout2.ui.fragment.HomeFragment;

import dagger.Module;
import dagger.Provides;

/**
 * HomeFragment业务类创建
 */
@Module
public class HomeFragmentModule {
    private HomeFragment fragment;

    public HomeFragmentModule(HomeFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    HomeFragmentPresenter provideHomeFragmentPresenter(){
        return new HomeFragmentPresenter(fragment);
    }
}
