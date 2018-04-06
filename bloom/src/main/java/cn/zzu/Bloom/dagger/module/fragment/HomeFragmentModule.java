package cn.zzu.Bloom.dagger.module.fragment;

import cn.zzu.Bloom.presenter.fragment.HomeFragmentPresenter;
import cn.zzu.Bloom.ui.fragment.HomeFragment;

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
