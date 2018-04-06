package cn.zzu.Bloom.dagger.module.fragment;

import cn.zzu.Bloom.presenter.fragment.OrderFragmentPresenter;
import cn.zzu.Bloom.ui.fragment.OrderFragment;

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
