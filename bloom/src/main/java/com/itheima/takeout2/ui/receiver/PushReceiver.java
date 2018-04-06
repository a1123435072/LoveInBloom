package com.itheima.takeout2.ui.receiver;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.itheima.takeout2.ui.observer.OrderObserver;
import com.itheima.takeout2.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Push消息处理receiver。请编写您需要的回调函数， 一般来说： onBind是必须的，用来处理startWork返回值；
 * onMessage用来接收透传消息； onSetTags、onDelTags、onListTags是tag相关操作的回调；
 * onNotificationClicked在通知被点击时回调； onUnbind是stopWork接口的返回值回调
 * 返回值中的errorCode，解释如下：
 * 0 - Success
 * 10001 - Network Problem
 * 10101  Integrate Check Error // 集成检查错误
 * 30600 - Internal Server Error
 * 30601 - Method Not Allowed
 * 30602 - Request Params Not Valid
 * 30603 - Authentication Failed
 * 30604 - Quota Use Up Payment Required
 * 30605 -Data Required Not Found
 * 30606 - Request Time Expires Timeout
 * 30607 - Channel Token Timeout
 * 30608 - Bind Relation Not Found
 * 30609 - Bind Number Too Many
 * 当您遇到以上返回错误时，如果解释不了您的问题，请用同一请求的返回值requestId和errorCode联系我们追查问题。
 */

public class PushReceiver extends PushMessageReceiver {
    /**
     * 接收通知到达的函数
     * context 上下文
     * title 推送的通知的标题
     * description 推送的通知的描述
     * customContentString 自定义内容，为空或者json字符串
     */
    @Override
    public void onNotificationArrived(Context context, String title,
                                      String description, String customContentString) {
        String notifyString = "通知到达 onNotificationArrived  title=\"" + title
                + "\" description=\"" + description + "\" customContent="
                + customContentString;
        Log.d(TAG, notifyString);

        // 自定义内容获取方式，mykey和myvalue对应通知推送时自定义内容中设置的键和值
        if (!TextUtils.isEmpty(customContentString)) {
            JSONObject customJson = null;
            try {
                customJson = new JSONObject(customContentString);
                String type = null;
                String orderId = null;
                String lat = null;
                String lng = null;
                if (!customJson.isNull("type")) {
                    type = customJson.getString("type");
                }
                if (!customJson.isNull("orderId")) {
                    orderId = customJson.getString("orderId");
                }

                if (!customJson.isNull("type")) {
                    type = customJson.getString("type");
                }
                if (!customJson.isNull("orderId")) {
                    orderId = customJson.getString("orderId");
                }
                if (!customJson.isNull(Constant.LAT)) {
                    lat = customJson.getString(Constant.LAT);
                }
                if (!customJson.isNull(Constant.LNG)) {
                    lng = customJson.getString(Constant.LNG);
                }

                HashMap<String, String> data = new HashMap<>();

                if (!TextUtils.isEmpty(type)) {
                    data.put("type", type);
                }
                if (!TextUtils.isEmpty(orderId)) {
                    data.put("orderId", orderId);
                }
                if (!TextUtils.isEmpty(lat)) {
                    data.put(Constant.LAT, lat);
                }
                if (!TextUtils.isEmpty(lng)) {
                    data.put(Constant.LNG, lng);
                }

                if (data.size() > 0) {
                    OrderObserver.getObserver().changeOrderInfo(data);
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 调用PushManager.startWork后，sdk将对push
     * server发起绑定请求，这个过程是异步的。绑定请求的结果通过onBind返回。 如果您需要用单播推送，需要把这里获取的channel
     * id和user id上传到应用server中，再调用server接口用channel id和user id给单个手机或者用户推送。
     * <p/>
     * context
     * BroadcastReceiver的执行Context
     * errorCode
     * 绑定接口返回值，0 - 成功
     * appid
     * 应用id。errorCode非0时为null
     * userId
     * 应用user id。errorCode非0时为null
     * channelId
     * 应用channel id。errorCode非0时为null
     * requestId
     * 向服务端发起的请求id。在追查问题时有用；
     *
     * 0	绑定成功
     10001	当前网络不可用，请检查网络
     10002	服务不可用，连接server失败
     10003	服务不可用，503错误
     10101	应用集成方式错误，请检查各项声明和权限
     20001	未知错误
     30600	服务内部错误
     30601	非法函数请求，请检查您的请求内容
     30602	请求参数错误，请检查您的参数
     30603	非法构造请求，服务端验证失败
     30605	请求的数据在服务端不存在
     * @return none
     */
    @Override
    public void onBind(Context context, int errorCode, String appid,
                       String userId, String channelId, String requestId) {
        String responseString = "onBind errorCode=" + errorCode + " appid="
                + appid + " userId=" + userId + " channelId=" + channelId
                + " requestId=" + requestId;
        Log.d(TAG, responseString);

        if (errorCode == 0) {
            // 绑定成功
            Log.d(TAG, "百度推送 = 绑定成功");
        }else{
            Log.d(TAG, "onBind: 百度推送绑定失败 ， 失败码为 = " +  errorCode);
        }
    }

    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }

    /**
     * 接收透传消息的函数
     * context 上下文
     * message 推送的消息
     * customContentString 自定义内容，为空或者json字符串
     */
    @Override
    public void onMessage(Context context, String message,
                          String customContentString) {
        String messageString = "透传消息 onMessage=\"" + message
                + "\" customContentString=" + customContentString;
        Log.d(TAG, messageString);

        // 自定义内容获取方式，mykey和myvalue对应透传消息推送时自定义内容中设置的键和值
        if (!TextUtils.isEmpty(customContentString)) {
            JSONObject customJson = null;
            try {
                customJson = new JSONObject(customContentString);
                String myvalue = null;
                if (!customJson.isNull("mykey")) {
                    myvalue = customJson.getString("mykey");
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 接收通知点击的函数
     * context 上下文
     * title 推送的通知的标题
     * description 推送的通知的描述
     * customContentString 自定义内容，为空或者json字符串
     */
    @Override
    public void onNotificationClicked(Context context, String title,
                                      String description, String customContentString) {
        String notifyString = "通知点击 onNotificationClicked title=\"" + title + "\" description=\""
                + description + "\" customContent=" + customContentString;
        Log.d(TAG, notifyString);

        // 自定义内容获取方式，mykey和myvalue对应通知推送时自定义内容中设置的键和值
        if (!TextUtils.isEmpty(customContentString)) {
            JSONObject customJson = null;
            try {
                customJson = new JSONObject(customContentString);
                String myvalue = null;
                if (!customJson.isNull("mykey")) {
                    myvalue = customJson.getString("mykey");
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}
