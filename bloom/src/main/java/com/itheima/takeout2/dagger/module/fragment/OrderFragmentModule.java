package com.itheima.takeout2.dagger.module.fragment;

import com.itheima.takeout2.presenter.fragment.OrderFragmentPresenter;
import com.itheima.takeout2.ui.fragment.OrderFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by itheima.
 */
@Module
public class OrderFragmentModule {


    OrderFragment frament;

    public OrderFragmentModule(OrderFragment frament) {
        this.frament = frament;
    }

    @Provides
    public OrderFragmentPresenter provideOrderFragmentPresenter(){
        return new OrderFragmentPresenter(frament);
    }
}
