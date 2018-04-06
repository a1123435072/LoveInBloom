package cn.zzu.Bloom.dagger.conponent.fragment;

import cn.zzu.Bloom.dagger.module.fragment.GoodsFragmentModule;
import cn.zzu.Bloom.ui.fragment.GoodsFragment;

import dagger.Component;

/**
 * Created by huaqing.
 */
@Component(modules = GoodsFragmentModule.class)
public interface GoodsFragmentConponent {
    void in(GoodsFragment fragment);
}
