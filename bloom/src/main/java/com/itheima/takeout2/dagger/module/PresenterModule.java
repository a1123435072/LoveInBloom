package com.itheima.takeout2.dagger.module;

import com.itheima.takeout2.presenter.activity.AddressPresenter;
import com.itheima.takeout2.presenter.activity.LoginActivityPresenter;
import com.itheima.takeout2.presenter.activity.OrderPresenter;
import com.itheima.takeout2.presenter.activity.PaymentPresenter;
import com.itheima.takeout2.ui.IView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by itheima.
 */
@Module
public class PresenterModule {
    private IView view;

    public PresenterModule(IView view) {
        this.view = view;
    }

    @Provides
    public OrderPresenter provideOrderPresenter(){
        return new OrderPresenter(view);
    }

    @Provides
    public AddressPresenter provideAddressPresenter(){
        return new AddressPresenter(view);
    }

    @Provides
    public PaymentPresenter providePaymentPresenter(){
        return new PaymentPresenter(view);
    }

    @Provides
    public LoginActivityPresenter provideLoginActivityPresenter(){
        return new LoginActivityPresenter(view);
    }



}
