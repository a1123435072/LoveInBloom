package cn.zzu.Bloom.dagger.conponent.fragment;

import cn.zzu.Bloom.dagger.module.fragment.OrderFragmentModule;
import cn.zzu.Bloom.ui.fragment.OrderFragment;

import dagger.Component;

/**
 * Created by huaqing.
 */
@Component(modules = OrderFragmentModule.class)
public interface OrderFragmentCommponet {
    void in(OrderFragment fragment);
}
