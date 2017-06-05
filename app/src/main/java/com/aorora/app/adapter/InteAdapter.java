package com.aorora.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aorora.app.R;
import com.aorora.app.base.BaseRecyclerAdapter;
import com.aorora.app.base.CommonHolder;
import com.aorora.app.bean.InteBean;
import com.aorora.app.uitls.ARLConfig;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.Bind;

/**
 * author : Administrator on 2017/5/27.
 * time : 2017/06/01
 * fileNmae : ${fileName}
 * desc : 积分换购列表适配器
 */
public class InteAdapter extends BaseRecyclerAdapter<InteBean> {


    @Override
    public CommonHolder<InteBean> setViewHolder(ViewGroup parent) {

        return new InteHolder(parent.getContext(), parent);
    }

    private MyItemClickListener mItemClickListener;

    public void setOnItemClickListener(MyItemClickListener mItemClickListener) {

        this.mItemClickListener = mItemClickListener;
    }

    public interface MyItemClickListener {

        void ItemClickListener(View view,int positon);

    }

    class InteHolder extends CommonHolder<InteBean> {


        @Bind(R.id.goods_image)
        ImageView mGoodsImage;
        @Bind(R.id.goods_title)
        TextView mGoodsTitle;
        @Bind(R.id.goods_price)
        TextView mGoodsPrice;
        @Bind(R.id.goods_integral)
        TextView mGoodsIntegral;
        @Bind(R.id.buyimmediately)
        Button mBuyimmediately;
        @Bind(R.id.buygoods_linear)
        LinearLayout mBuygoodsLinear;
        private Context context;

        public InteHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.buygoods_item);

            this.context = context;
        }

        @Override
        public void bindData(InteBean inteBean, final int position) {


            mGoodsTitle.setText(inteBean.name + "");
            if (inteBean.price == null
                    || inteBean.price.equals("")) {
                mGoodsPrice.setText("");
            } else {
                mGoodsPrice.setText("￥" + inteBean.price);
            }


            mBuyimmediately.setText("立即换购");


            Glide.with(context).load(ARLConfig.IMAGEURL + inteBean.thumbnail)
                    .centerCrop()
                    .placeholder(R.mipmap.default_image)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(mGoodsImage);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (null != mItemClickListener) {
                        mItemClickListener.ItemClickListener(v,position);
                    }

                }
            });

        }
    }


}
