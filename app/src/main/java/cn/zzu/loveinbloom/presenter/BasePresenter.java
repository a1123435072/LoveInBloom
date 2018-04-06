package cn.zzu.loveinbloom.presenter;

import android.util.Log;

import cn.zzu.loveinbloom.model.ResponseIfnfo;
import cn.zzu.loveinbloom.model.dao.DBHelper;
import cn.zzu.loveinbloom.module.net.ResponseInfoAPI;
import cn.zzu.loveinbloom.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangg on 2018/3/16.
 * 业务层公共代码封装
 * <p>
 * 抽取业务层联网代码
 */

public abstract class BasePresenter {

    private static final String TAG  = BasePresenter.class.getSimpleName();
    public ResponseInfoAPI responseInfoAPI;

    protected DBHelper helper;

    public BasePresenter() {

        //网络
        if (responseInfoAPI == null) {
            Retrofit retrofit = new Retrofit.Builder().
                    baseUrl(Constant.BASE_URL).
                    addConverterFactory(GsonConverterFactory.create())
                    .build();
            responseInfoAPI = retrofit.create(ResponseInfoAPI.class);
        }

        helper  = DBHelper.getInstance();
    }

    public class CallbackAdapter implements Callback<ResponseIfnfo> {

        @Override
        public void onResponse(Call<ResponseIfnfo> call, Response<ResponseIfnfo> response) {
            // 处理回复
            if (response != null && response.isSuccessful()) {
                ResponseIfnfo info = response.body();

                if("0".equals(info.code)){
                    Log.d(TAG,"info.code --> "+info.code);
                    // 服务器端处理成功，并返回目标数据
                    //parserData(info.data);
                }else{
                    // 服务器端处理成功，返回错误提示，该信息需要展示给用户
                    // 依据code值获取到失败的数据
                    //String msg = ErrorInfo.INFO.get(info.code);
                    //failed(msg);
                }

            }
        }

        @Override
        public void onFailure(Call<ResponseIfnfo> call, Throwable t) {

        }
    }
}