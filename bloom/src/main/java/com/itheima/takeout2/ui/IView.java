package com.itheima.takeout2.ui;

/**
 * 所有界面需要实现的接口
 */
public interface IView {

    void success(Object o);
    void failed(String msg);
}
