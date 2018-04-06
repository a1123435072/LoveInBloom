package com.itheima.takeout2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.takeout2.R;
import com.itheima.takeout2.model.dao.bean.UserBean;
import com.itheima.takeout2.utils.PromptManager;
import com.itheima.takeout2.utils.SMSUtil;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 用户登录界面：
 * 1、引入短信校验相关工具
 * 2、获取验证码
 * 等待时间处理
 * 3、发送验证码
 * 4、把手机号码发送到自己的服务器，做注册或登陆操作
 */
public class LoginActivity extends BaseActivity {

    /*
    App Key：16db05f4edda0
App Secret：f5f8e5c52e11337353979972c1b08cae

     */


    private static final String APPKEY = "16db05f4edda0";
    private static final String APPSECRET = "f5f8e5c52e11337353979972c1b08cae";
    private static final int SENDING = -9;
    private static final int RESEND = -8;
    private static final String TAG = "LoginActivity";
    @InjectView(R.id.iv_user_back)
    ImageView ivUserBack;
    @InjectView(R.id.iv_user_password_login)
    TextView ivUserPasswordLogin;
    @InjectView(R.id.et_user_phone)
    EditText etUserPhone;
    @InjectView(R.id.tv_user_code)
    TextView tvUserCode;
    @InjectView(R.id.et_user_code)
    EditText etUserCode;
    @InjectView(R.id.login)
    TextView login;
    private String phone;
    private int i = 60;//倒计时


    /**
     * 1、	权限校验: SMSUtil.checkPermission(this);
     * 2、	初始化工具: SMSSDK.initSDK(this, APPKEY, APPSECRET, true);
     * 3、	注册事件监听：SMSSDK.registerEventHandler(eventHandler);
     * 4、	获取验证码：SMSSDK.getVerificationCode("86", phone);监听事件触发。
     * 5、	发送验证码：SMSSDK.submitVerificationCode("86", phone, code.trim());监听事件触发。
     * 6、	注销监听。
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        //权限校验:
        SMSUtil.checkPermission(this);
        //初始化工具:
        SMSSDK.initSDK(this, APPKEY, APPSECRET, true);

        //注册事件监听：
        SMSSDK.registerEventHandler(eventHandler);

    }

    @OnClick({R.id.tv_user_code, R.id.login, R.id.iv_user_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_user_code:
                // 校验用户输入信息
                phone = etUserPhone.getText().toString();
                boolean judgePhoneNums = SMSUtil.judgePhoneNums(this, phone);
                if (!judgePhoneNums) {
                    return;
                }


                // 获取验证码
                SMSSDK.getVerificationCode("86", phone);

                // 修改发送按钮信息及状态
                tvUserCode.setEnabled(false);
                tvUserCode.setText("重新发送（" + i + "）");
                new Thread() {
                    @Override
                    public void run() {
                        for (; i > 0; i--) {
                            handler.sendEmptyMessage(SENDING);
                            if (i <= 0)
                                break;
                            SystemClock.sleep(999);
                        }
                        handler.sendEmptyMessage(RESEND);
                    }
                }.start();

                break;
            case R.id.login:
                // 发送验证码
                String code = etUserCode.getText().toString();
                if (!TextUtils.isEmpty(code)) {
                    SMSSDK.submitVerificationCode("86", phone, code);
                    PromptManager.showProgressDialog(this);
                }

                test();
                break;
            case R.id.iv_user_back:
                this.finish();
                break;
        }
    }

    private void test() {
        HashMap<String, String> params = new HashMap<>();
        params.put("type","2");
        params.put("phone","13200000000");
        presenter.login(params);
    }


    private EventHandler eventHandler = new EventHandler() {
        @Override
        public void afterEvent(final int event, final int result, final Object data) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //处理验证码结果
                    doSMSCode(event, result, data);
                }
            });

        }

    };


public void doSMSCode(int event, int result, Object  data){
    if (result == SMSSDK.RESULT_COMPLETE) {
        //回调完成
        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
            //提交验证码成功(服务器短信验证通过)
            Log.i(TAG, "验证通过了");
            // 处理登陆操作
            HashMap<String, String> params = new HashMap<>();
            params.put("type","2");
            params.put("phone",phone);
            presenter.login(params);
        } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
            //获取验证码成功（服务器发送验证码成功）
            Log.i(TAG, "获取验证码成功 = 服务器已经发送验证码");
            Toast.makeText(LoginActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
        }
    } else {
        if(data != null && data instanceof Throwable){
            String message = ((Throwable) data).getMessage();
            Toast.makeText(LoginActivity.this, "短信码获取失败 = " + message, Toast.LENGTH_SHORT).show();
            Log.d("LoginActivity", "短信码获取失败 = " + message);
        }
        PromptManager.closeProgressDialog();
    }
}






    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SENDING) {
                tvUserCode.setText("重新发送（" + i + "）");
            } else if (msg.what == RESEND) {
                tvUserCode.setText("获取验证码");
                tvUserCode.setEnabled(true);
            }
        }
    };


    @Override
    public void success(Object o) {
        PromptManager.closeProgressDialog();
        if(o != null && o instanceof UserBean){
            String name = ((UserBean) o).getName();
            String phone = ((UserBean) o).getPhone();
            Bundle  bundle = new Bundle();
            bundle.putSerializable("userBean", (UserBean)o);
            Intent  intent = new Intent();
            intent.putExtras(bundle);
            setResult(0x1000, intent);
            Toast.makeText(LoginActivity.this, "用户登录成功: name = " + name + "; phone = " + phone, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(LoginActivity.this, "用户登录失败", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
