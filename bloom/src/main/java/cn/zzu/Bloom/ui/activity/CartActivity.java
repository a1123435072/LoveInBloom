package cn.zzu.Bloom.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.zzu.Bloom.MyApplication;
import com.zzu.Bloom.R;
import cn.zzu.Bloom.model.net.bean.GoodsInfo;
import cn.zzu.Bloom.ui.ShoppingCartManager;
import cn.zzu.Bloom.ui.views.RecycleViewDivider;
import cn.zzu.Bloom.utils.NumberFormatUtils;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 购物车界面
 */
public class CartActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_total)
    TextView tvTotal;
    @InjectView(R.id.tv_money)
    TextView tvMoney;
    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.cart_rv)
    RecyclerView cartRv;
    private MyCartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.inject(this);

        toolbar.setTitle("购物车");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter=new MyCartAdapter();
        cartRv.setAdapter(adapter);
        cartRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        // 设置分割线
        cartRv.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,1,0XE3E0DC));


        tvMoney.setText(NumberFormatUtils.formatDigits(ShoppingCartManager.getInstance().getMoney()/100.0));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.button)
    public void onClick() {
        // TODO 登陆入口一
        // 判断是否登陆了
        // 如果登陆了，去订单生成界面
        // 没有登陆，去用户登陆界面

        Intent intent =null;
        if(MyApplication.USERID != 0){
            // 如果登陆了，去订单生成界面
            intent = new Intent(this, SettleCenterActivity.class);
        }else{
            // 没有登陆，去用户登陆界面
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);

    }

    class MyCartAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(View.inflate(MyApplication.getContext(), R.layout.item_cart, null));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ViewHolder) holder).setData(ShoppingCartManager.getInstance().goodsInfos.get(position));
        }

        @Override
        public int getItemCount() {
            return ShoppingCartManager.getInstance().goodsInfos.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @InjectView(R.id.item_iv)
            ImageView itemIv;
            @InjectView(R.id.item_tv_name)
            TextView itemTvName;
            @InjectView(R.id.item_tv_price)
            TextView itemTvPrice;
            @InjectView(R.id.item_tv_num)
            TextView itemTvNum;

            private GoodsInfo data;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.inject(this, this.itemView);
            }

            public void setData(GoodsInfo data) {
                this.data = data;
                Picasso.with(MyApplication.getContext()).load(data.icon).into(itemIv);
                itemTvName.setText(data.name);
                itemTvPrice.setText(NumberFormatUtils.formatDigits(data.newPrice));
                itemTvNum.setText(data.count+"");
            }
        }



    }
}
