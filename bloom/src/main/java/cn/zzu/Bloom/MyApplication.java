package cn.zzu.Bloom;

import android.app.Application;
import android.content.Context;

import com.amap.api.services.core.LatLonPoint;

/**
 * Created by huaqing.  定义的全局的属性,可以从这里面拿到很多常用的配置信息
 *                      比如
 *                      1, ip地址  app 请求网络   访问的服务器 ,全局都可以取到了  开发中常用,可以非常方便 :内存中一直存在
 *                      2, Context  应用程序需要使用 上下文 的时候可以直接拿到全局的Context 真实工作的时候比建议这样用
 */

public class MyApplication extends Application {
    public static LatLonPoint LOCATION = null;
    private static Context context;

    //public static String ip = "10.0.2.2";
    public static String ip = "192.168.2.162";
//    public static String ip = "192.168.125.31";


//    public static String ip = "192.168.191.1";

    public static Context getContext() {
        return context;
    }

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }
    // 记录用户信息
//    public static int USERID=0;
//    public static String phone="";
    // 测试数据2163
    public static int USERID = -1;
    public static String phone="13280000000";

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
//        PushManager.startWork(this, PushConstants.LOGIN_TYPE_API_KEY, "KW2yAQYszKUck4iiAOGIaOMl");
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
//        MobclickAgent.setScenarioType(context, MobclickAgent.EScenarioType.E_UM_NORMAL);

        // 关闭Activity的默认统计方式，我们需要统计Activity+Fragment
//        MobclickAgent.openActivityDurationTrack(false);
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }
}
