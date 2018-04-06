package com.itheima.takeout2.dagger.conponent;

import com.itheima.takeout2.dagger.module.PresenterModule;
import com.itheima.takeout2.ui.activity.BaseActivity;

import dagger.Component;

/**
 * Created by itheima.
 */
@Component(modules = PresenterModule.class)
public interface CommonConponent {
    void in(BaseActivity view);// in 的对象？  需要有@Inject的类
}
