package com.aorora.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aorora.app.R;
import com.aorora.app.bean.GiftBean;
import com.aorora.app.uitls.ARLConfig;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * author : Administrator on 2017/5/26.
 * time : 2017/06/01
 * fileNmae : ${fileName}
 * desc : 礼品中心列表适配器
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private List<GiftBean> list;
    private Context mContext;
    public MyAdapter(List<GiftBean> list) {
        this.list = list;
    }

    private MyItemClickListener mItemClickListener;

    public void setOnItemClickListener(MyItemClickListener mItemClickListener){

        this.mItemClickListener = mItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.gift_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        holder.name.setText(list.get(position).name + "");
        if (list.get(position).price == null
                || list.get(position).price.equals("")) {
            holder.price.setText("");
            holder.price.setVisibility(View.GONE);//礼品中心没有该属性 隐藏
        } else {

            holder.price.setText("￥" + list.get(position).price);
            holder.price.setVisibility(View.GONE);//礼品中心没有该属性 隐藏
        }

        if (list.get(position).needIntegral == null) {
            holder.number.setVisibility(View.GONE);
        }

        holder.receive.setText("立即领取");

        Glide.with(mContext).load(ARLConfig.IMAGEURL + list.get(position).thumbnail)
                .centerCrop()
                .placeholder(R.mipmap.default_image)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.image);

        if(null!=mItemClickListener){

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = holder.getLayoutPosition();
                    mItemClickListener.onItemClickListener(holder.itemView,pos); //设置回调  activity实现回调
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return list.size()>0?list.size():0;
    }

    public interface MyItemClickListener{

        void onItemClickListener(View view,int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView price;
        TextView number;
        ImageView image;
        Button receive;
        LinearLayout gift_linear;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.goods_title);
            price = (TextView) itemView
                    .findViewById(R.id.goods_price);
            number = (TextView) itemView
                    .findViewById(R.id.goods_integral);
            image = (ImageView) itemView
                    .findViewById(R.id.goods_image);
            receive = (Button) itemView.findViewById(R.id.receive);
            gift_linear = (LinearLayout) itemView
                    .findViewById(R.id.gift_linear);
        }
    }
}
