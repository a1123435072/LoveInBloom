package com.itheima.takeout2.ui.adapter;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.takeout2.MyApplication;
import com.itheima.takeout2.R;
import com.itheima.takeout2.model.net.bean.GoodsTypeInfo;

import java.util.ArrayList;

/**
 * 商品信息类别列表数据适配
 */

public class MyHeadAdapter extends BaseAdapter {
    private ArrayList<GoodsTypeInfo> headDataSet;
    private int selectedHeadIndex;

    public MyHeadAdapter(ArrayList<GoodsTypeInfo> headDataSet) {
        this.headDataSet = headDataSet;
    }


    @Override
    public int getCount() {
        return headDataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return headDataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GoodsTypeInfo data = headDataSet.get(position);
        HeadViewHolder holder;
        if (convertView == null) {
            convertView = new TextView(parent.getContext());
            holder = new HeadViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HeadViewHolder) convertView.getTag();
        }
        holder.setData(data);

        if (position == selectedHeadIndex)
            holder.itemView.setBackgroundColor(Color.WHITE);


        return convertView;
    }

    public void setSelectedPositon(int index) {
        selectedHeadIndex = index;
        notifyDataSetChanged();
    }

    private class HeadViewHolder {

        private View itemView;
        private GoodsTypeInfo data;

        public HeadViewHolder(View itemView) {
            this.itemView = itemView;
        }

        public void setData(GoodsTypeInfo data) {
            this.data = data;
            ((TextView) itemView).setText(data.name);
            ((TextView) itemView).setBackgroundColor(MyApplication.getContext().getResources().getColor(R.color.colorItemBg));
            ((TextView) itemView).setTextSize(12);

            //applyDimension的作用：根据指定的单位以及数值，转换为px
            // dp - px =  50 * density（像素密度）
            // px - px =  50
            // sp - px =  50 *  scaleDensity （像素密度）注意： 针对字体转换
            int h = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, itemView.getResources().getDisplayMetrics())+0.5f);


//            总结：
//            以上的转换方式和我们之前讲过的代码适配实现是一样的。
//            只不过，单位的转换，google公司封装了一个TypeValue中实现了。
//            和我们以上px和dp互转的封装的实现是一样的。

            //查看  DensityUtil 类中的 px 和dp互转的代码实现





            ((TextView) itemView).setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, h));
            ((TextView) itemView).setGravity(Gravity.CENTER);
        }
    }
}