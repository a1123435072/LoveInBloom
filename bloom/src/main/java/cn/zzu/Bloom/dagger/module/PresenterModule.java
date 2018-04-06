package cn.zzu.Bloom.dagger.module;

import cn.zzu.Bloom.presenter.activity.AddressPresenter;
import cn.zzu.Bloom.presenter.activity.LoginActivityPresenter;
import cn.zzu.Bloom.presenter.activity.OrderPresenter;
import cn.zzu.Bloom.presenter.activity.PaymentPresenter;
import cn.zzu.Bloom.ui.IView;

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
