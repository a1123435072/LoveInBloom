package cn.zzu.Bloom.dagger.module.fragment;

import cn.zzu.Bloom.presenter.fragment.GoodsFragmentPresenter;
import cn.zzu.Bloom.ui.fragment.GoodsFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huaqing.
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
