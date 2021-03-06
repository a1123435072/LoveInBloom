package cn.zzu.Bloom.presenter.fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cn.zzu.Bloom.model.net.bean.Order;
import cn.zzu.Bloom.model.net.bean.ResponseInfo;
import cn.zzu.Bloom.presenter.BasePresenter;
import cn.zzu.Bloom.ui.fragment.OrderFragment;

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
