package cn.zzu.Bloom.dagger.conponent.fragment;

import cn.zzu.Bloom.dagger.module.fragment.HomeFragmentModule;
import cn.zzu.Bloom.ui.fragment.HomeFragment;

import dagger.Component;

/**
 * 将创建好的业务对象设置给目标
 */
@Component(modules = HomeFragmentModule.class)
public interface HomeFragmentConponent {
    void in(HomeFragment fragment);
}
