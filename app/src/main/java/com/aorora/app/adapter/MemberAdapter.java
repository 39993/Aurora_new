package com.aorora.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aorora.app.R;
import com.aorora.app.bean.MyMemberClass;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Administrator on 2017/6/3.
 * time : 2017/06/03
 * fileNmae : ${fileName}
 * desc :
 */
public class MemberAdapter extends BaseAdapter {

    private List<MyMemberClass> data = new ArrayList<>();
    private Context mContext;

    public MemberAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<MyMemberClass> list){
        data.clear();
        if (null==list)return;
        data.addAll(list);
        notifyDataSetChanged();

    }

    public void setAall(List<MyMemberClass> list){

        if (null==list)return;
        data.addAll(list);
        notifyDataSetChanged();

    }


    @Override
    public int getCount() {
        return data.size()>0?data.size():0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder ;

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.mymember_item, null);
            holder.identity = (TextView) convertView.findViewById(R.id.identity);
            holder.promotionnumber = (TextView) convertView
                    .findViewById(R.id.promotionnumber);
            holder.number = (TextView) convertView
                    .findViewById(R.id.number);
            holder.image = (ImageView) convertView
                    .findViewById(R.id.image);
            holder.weixinnumber = (TextView) convertView.findViewById(R.id.weixinnumber);
            holder.weixinname = (TextView) convertView
                    .findViewById(R.id.weixinname);
            holder.hisorder = (TextView) convertView
                    .findViewById(R.id.hisorder);
            holder.hismember = (TextView) convertView
                    .findViewById(R.id.hismember);

            holder.mem_phone = (TextView) convertView.findViewById(R.id.mem_phone);
            holder.mem_time = (TextView) convertView.findViewById(R.id.mem_time);
            holder.mem_num = (TextView) convertView.findViewById(R.id.mem_num);

            holder.lin_item = (LinearLayout) convertView.findViewById(R.id.lin_item);

            holder.lin_item.setTag(position);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        MyMemberClass info = data.get(position);


        if(null==info.getPhone()){
            holder.mem_phone.setText("手机"+info.getMobile());
        }else{
            holder.mem_phone.setText("手机"+info.getPhone());
        }

        if(null==info.getDirectTotalCount()){

            holder.mem_num.setText("注册时间："+info.getCreateTime());
        }else{
            holder.mem_num.setText("推荐人数"+info.getDirectTotalCount());
        }

        if(null==info.getRegisterTime()){
            holder.mem_time.setText("资质有效期："+info.getRedPaperExpireTime());
        }else{
            holder.mem_time.setText("注册时间"+info.getRegisterTime());
        }


        holder.identity.setText("身份："+data.get(position).getGradeName() + "");
        holder.promotionnumber.setText("推广人编号:"+data.get(position).getParentNumber() + "");
        holder.weixinnumber.setText("微信号:"+data.get(position).getWeixin() + "");
        holder.weixinname.setText("昵称:"+data.get(position).getWeixinName() + "");

        holder.number.setText("会员编号："+data.get(position).getNumber() + "");

        //他的订单
        holder.hisorder.setVisibility(View.GONE);

        if(null==data.get(position).getHeadUrl()){

            holder.image.setImageResource(R.mipmap.default_image);
        }else{

            Glide.with(mContext)
                    .load(data.get(position).getHeadUrl())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.image);

        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(data.get(position).getIsput()){

                        holder.lin_item.setVisibility(View.VISIBLE);
                        data.get(position).setIsput(false);


                }else{


                        holder.lin_item.setVisibility(View.GONE);
                        data.get(position).setIsput(true);

                }

            }
        });


        return convertView;

    }

    private static class ViewHolder{

        TextView mem_phone,mem_time,mem_num;

        TextView number;
        TextView identity;
        TextView promotionnumber;
        TextView weixinnumber;
        TextView weixinname;
        TextView hisorder;
        TextView hismember;
        ImageView image;
        LinearLayout lin_item;

    }


}
