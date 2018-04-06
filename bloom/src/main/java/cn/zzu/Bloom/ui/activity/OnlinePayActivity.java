package cn.zzu.Bloom.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.zzu.Bloom.R;
import cn.zzu.Bloom.model.net.bean.PaymentInfo;
import cn.zzu.Bloom.ui.ShoppingCartManager;
import cn.zzu.Bloom.utils.PayUtils;
import cn.zzu.Bloom.utils.pay.PayResult;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 在线支付界面
 */
public class OnlinePayActivity extends BaseActivity {

    // 1.获取支付信息展示给用户
    // 2.支付



    @InjectView(R.id.ib_back)
    ImageButton ibBack;
    @InjectView(R.id.tv_residualTime)
    TextView tvResidualTime;
    @InjectView(R.id.tv_order_name)
    TextView tvOrderName;
    @InjectView(R.id.ll_order_detail)
    LinearLayout llOrderDetail;
    @InjectView(R.id.tv_pay_money)
    TextView tvPayMoney;
    @InjectView(R.id.ll_pay_type_container)
    LinearLayout llPayTypeContainer;
    @InjectView(R.id.bt_confirm_pay)
    Button btConfirmPay;

    private String orderId;


    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);

                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();

                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(OnlinePayActivity.this, "支付成功",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(OnlinePayActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(OnlinePayActivity.this, "支付失败",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };




   private static final int SDK_PAY_FLAG = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_pay);

        ButterKnife.inject(this);
        orderId = getIntent().getStringExtra("orderId");
        tvOrderName.setText("第"+orderId+"号订单");

        paymentPresenter.getData(orderId);

    }



    @OnClick({R.id.bt_confirm_pay})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_confirm_pay:
                //确认支付模块
                pay();
                break;
        }
    }

    /**
     * call alipay sdk pay. 调用SDK支付
     *
     */
    public void pay() {
        // 订单
        String orderInfo = PayUtils.getOrderInfo(ShoppingCartManager.getInstance().name, "通过黑马程序员订购的", "0.01");

        // 对订单做RSA 签名
        String sign = PayUtils.sign(orderInfo);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + PayUtils.getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(OnlinePayActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    @Override
    public void success(Object o) {

        if(o instanceof HashMap){

            HashMap<String, Object> data = (HashMap<String, Object>) o;
            int payDownTime = (int) data.get("payDownTime");

            mHandler.post(new MyResidualTimerTask(payDownTime * 60));
            tvPayMoney.setText("￥" + data.get("money").toString());
            List<PaymentInfo> payments = (List<PaymentInfo>) data.get("paymentInfo");
            addPayment(payments);
        }
    }


    // 处理支付方式的选择：
    // 在支付条目添加时候，获取所有条目的CheckBox，将这些CheckBox引用添加到一个容器，当某个CheckBox被选中的时候需要循环该容器，其他的全部修改为没有选中状态

    ArrayList<CheckBox> cbs=new ArrayList<>();

    private void addPayment(List<PaymentInfo> payments) {
        for ( PaymentInfo item : payments) {
            // 分割线
            View view = new View(this);
            view.setBackgroundColor(0xfd9b9999);
            int h = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()) + .5);
            llPayTypeContainer.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, h);

            view = View.inflate(this, R.layout.item_online_pay, null);
            Picasso.with(this).load(item.url).into((ImageView) view.findViewById(R.id.iv_pay_logo));
            ((TextView) view.findViewById(R.id.tv_pay_name)).setText(item.name);
            CheckBox cb = (CheckBox) view.findViewById(R.id.cb_pay_selected);

            // 如何做到点击某个CheckBox知道起对应的支付信息
            cb.setTag(item.id);
            cbs.add(cb);

            llPayTypeContainer.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        setCheckBoxCheckedListener();

    }

    private  int paymentType=-1;
    private void setCheckBoxCheckedListener() {
        for(CheckBox cb :cbs){
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // 如果集合中的与传递进来的buttonView相等，说明该cb为用户选择的，其他的需要调整选择状态

                    if(isChecked){
                        for(CheckBox item:cbs){
                            if(item!=buttonView){
                                item.setChecked(false);
                            }else{
                                paymentType= (int) item.getTag();
                            }
                        }
                    }
                }
            });
        }
    }

    private class MyResidualTimerTask implements Runnable {

        private int time;

        public MyResidualTimerTask(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            time = time - 1;
            if (time == 0) {
                btConfirmPay.setEnabled(false);
            }
            int m = time / 60;
            int s = time % 60;
            tvResidualTime.setText("支付剩余时间:" + m + "分" + s + "秒");
            mHandler.postDelayed(this, 999);
        }
    }

}
