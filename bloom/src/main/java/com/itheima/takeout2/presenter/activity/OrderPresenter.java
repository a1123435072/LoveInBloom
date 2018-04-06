package com.itheima.takeout2.presenter.activity;

import android.util.Log;

import com.google.gson.Gson;
import com.itheima.takeout2.model.net.bean.Cart;
import com.itheima.takeout2.model.net.bean.GoodsInfo;
import com.itheima.takeout2.model.net.bean.OrderOverview;
import com.itheima.takeout2.model.net.bean.ResponseInfo;
import com.itheima.takeout2.presenter.BasePresenter;
import com.itheima.takeout2.ui.IView;
import com.itheima.takeout2.ui.ShoppingCartManager;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * 订单业务处理类
 */

public class OrderPresenter extends BasePresenter {
    // 1.生成订单——结算中心
    // 2.订单列表查询——订单列表界面
    // 3.订单详情查询——详情展示

    private IView view;

    public OrderPresenter(IView view) {
        this.view = view;
    }

    @Override
    protected void failed(String msg) {

    }



    int operation = 0;// 操作的标识

    public void create(int userid, int addressId, int type) {
        operation = 1;

        OrderOverview overview = new OrderOverview();
        overview.addressId = addressId;
        overview.sellerid = ShoppingCartManager.getInstance().sellerId;
        overview.type = type;
        overview.userId = userid;

        overview.cart = new ArrayList<>();
        for (GoodsInfo info : ShoppingCartManager.getInstance().goodsInfos) {
            Cart cart = new Cart();
            cart.id = info.id;
            cart.count = info.count;

            overview.cart.add(cart);
        }


        // 发送数据到服务器端（POST）

        Gson gson = new Gson();
        String json = gson.toJson(overview);
        Call<ResponseInfo> order = responseInfoAPI.creatOrder(json);
        order.enqueue(new CallbackAdapter());

    }


    @Override
    protected void parserData(String data) {
        Log.i("Test", data);

        switch(operation){
            case 1:
                view.success(data);
                break;
            case 2:
//                Gson gson=new Gson();
//                List<Order> orders=gson.fromJson(data,new TypeToken<List<Order>>(){}.getType());
//                view.success(orders);
                break;
        }

    }
}
