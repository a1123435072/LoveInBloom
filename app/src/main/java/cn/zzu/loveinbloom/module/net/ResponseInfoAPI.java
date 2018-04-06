package cn.zzu.loveinbloom.module.net;

import android.provider.SyncStateContract;

import cn.zzu.loveinbloom.model.ResponseIfnfo;
import cn.zzu.loveinbloom.utils.Constant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static android.R.attr.type;

/**
 * Created by yangg on 2018/3/19.
 */

public interface ResponseInfoAPI {

    //login ? username = xxx  登录的时候调用的方法
    @GET(Constant.LOGIN)
    Call<ResponseIfnfo> login(@Query("username") String username,
                             @Query("password") String password,
                             @Query("type") String type);



}
