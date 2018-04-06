package com.itheima.takeout2.presenter.fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima.takeout2.model.net.bean.Order;
import com.itheima.takeout2.model.net.bean.ResponseInfo;
import com.itheima.takeout2.presenter.BasePresenter;
import com.itheima.takeout2.ui.fragment.OrderFragment;

import java.util.List;

import retrofit2.Call;

/**
 * Created by itheima.
 */
public class OrderFragmentPresenter extends BasePresenter {

    private OrderFragment fragment;

    public OrderFragmentPresenter(OrderFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * 获取用户的订单列表数据
     */
    public void getOrderInfo() {
        Call<ResponseInfo> order = responseInfoAPI.order(1);
        order.enqueue(new CallbackAdapter());
    }


    @Override
    protected void failed(String message) {

    }

    @Override
    protected void parserData(String data) {
        Gson gson = new Gson();
//        Order order = gson.fromJson(data, Order.class);

        List<Order> orderList = gson.fromJson(data, new TypeToken<List<Order>>() {
        }.getType());

        // 更新界面：adapter
        fragment.getAdapter().setOrderList(orderList);
        fragment.getAdapter().notifyDataSetChanged();
        // 隐藏滚动条
        fragment.closeRefresh();
    }
}
