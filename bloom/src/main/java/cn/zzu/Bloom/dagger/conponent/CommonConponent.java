package cn.zzu.Bloom.dagger.conponent;

import cn.zzu.Bloom.dagger.module.PresenterModule;
import cn.zzu.Bloom.ui.activity.BaseActivity;

import dagger.Component;

/**
 * Created by huaqing.
 */
@Component(modules = PresenterModule.class)
public interface CommonConponent {
    void in(BaseActivity view);// in 的对象？  需要有@Inject的类
}
