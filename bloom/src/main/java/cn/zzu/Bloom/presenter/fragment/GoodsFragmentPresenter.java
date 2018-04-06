package cn.zzu.Bloom.presenter.fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cn.zzu.Bloom.model.net.bean.GoodsTypeInfo;
import cn.zzu.Bloom.model.net.bean.ResponseInfo;
import cn.zzu.Bloom.presenter.BasePresenter;
import cn.zzu.Bloom.ui.fragment.GoodsFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


/**
 * 商品数据业务处理
 */

public class GoodsFragmentPresenter extends BasePresenter {

    private GoodsFragment fragment;

    public GoodsFragmentPresenter(GoodsFragment fragment) {
        this.fragment = fragment;
    }

    public void getData(long sellerId) {
        // 联网获取数据
        Call<ResponseInfo> goods = responseInfoAPI.goods(sellerId);
        goods.enqueue(new CallbackAdapter());
    }


    @Override
    protected void failed(String msg) {

    }

    @Override
    protected void parserData(String data) {
        Gson gson = new Gson();
        ArrayList<GoodsTypeInfo> goodsTypeInfos = gson.fromJson(data, new TypeToken<List<GoodsTypeInfo>>() {
        }.getType());
        // 更新界面
        fragment.success(goodsTypeInfos);
    }
}
