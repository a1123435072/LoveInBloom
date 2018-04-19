package cn.zzu.Bloom.utils;

import cn.zzu.Bloom.MyApplication;

/**
 * Created by wuyue on 2016/9/2.
 */
public interface Constant {

    String ip = MyApplication.ip;
    //192.168.199.215     真是的服务器地址
    //192.168.82.250
    String replace_img_url = ip;
    // http://localhost:8080/   TakeoutService    /login?username="itheima"&password="bj"

    String BASEURL = "http://" + ip + ":8083/";
    //    String BASEURL="http://192.168.199.215:8083/";
    // 登陆
    //String LOGIN = "login";
    String LOGIN = "login";
    // http://localhost:8080/home
    // String HOME = "home";
    String HOME = "home";
    // http://localhost:8080/goods?sellerId=1
    //String GOODS = "goods";
    String GOODS = "goods";
    // http://localhost:8080/address?userId=2163&&&&&&
    String ADDRESS = "address";

    String ORDER = "order";
    String PAY = "pay";

    // 短信登陆的分类值
    int LOGIN_TYPE_SMS = 2;

    String LAT = "Lat";
    String LNG = "Lng";


    public static int EDIT_ADDRESS_REQUESTCODE = 0x100;
    public static int ADD_ADDRESS_REQUESTCODE = 0x200;
    public static int DELETE_ADDRESS_REQUESTCODE = 0x300;

}
