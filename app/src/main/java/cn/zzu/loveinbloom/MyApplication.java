package cn.zzu.loveinbloom;

import android.app.Application;
import android.content.Context;

/**
 * Created by yangg on 2018/3/27.
 */

public class MyApplication extends Application{

    private static Context context;

    //获取到当前应用的上下文
    public static Context getContext(){
        return context;
    }

    private static MyApplication instance;
    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context  = this;
    }
}
