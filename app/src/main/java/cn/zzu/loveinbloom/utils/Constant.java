package cn.zzu.loveinbloom.utils;

/**
 * Created by yangg on 2018/3/19.
 * 利用 这些常用字段将这些 字段拼接成服务器地址
 */

public interface Constant {
    //服务器地址
    //http://localhost:8080/TakeoutService/login?username="   "&password="  "  拼接后的地址示例
    //声明很多常用的字段
    String BASE_URL = "http://10.0.2.2:8080/";
    //服务器主页访问的地址
    String HOME = "TakeoutService/home";
    //推荐的地址
    String GOODS  = "TakeoutService/goods";
    //登陆的时候调用的接口
    String LOGIN = "TakeoutService/login";
    //订单
    String ORDER ="TakeoutService/order";

}
