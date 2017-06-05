package com.aorora.app.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.aorora.app.R;
import com.aorora.app.bean.PicturesClass;
import com.aorora.app.uitls.ARLConfig;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * author : Administrator on 2017/6/1.
 * time : 2017/06/01
 * fileNmae : ${fileName}
 * desc : 产品详情页图文详情下的图片列表
 */
public class PhotoAdapter extends BaseAdapter{


    private List<PicturesClass> path;
    private Context mContext;

    public PhotoAdapter(List<PicturesClass> path, Context context) {
        this.path = path;
        mContext = context;
    }

    @Override
    public int getCount() {
        return path.size()>0?path.size():0;
    }

    @Override
    public Object getItem(int position) {
        return path.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder ;

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.photo_item, null);

            holder.image = (ImageView) convertView.findViewById(R.id.photo);
            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wn = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wn.getDefaultDisplay().getMetrics(metric);

        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）

        ViewGroup.LayoutParams params =holder.image.getLayoutParams();
        params.height = width*3/4;
        params.width = width;
        holder.image.setLayoutParams(params);

        Glide.with(mContext)
                .load(ARLConfig.IMAGEURL+path.get(position).path)
                .centerCrop()
                .placeholder(R.mipmap.default_image)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.image);

        return convertView;
    }

    static class ViewHolder{

        ImageView image;


    }

}
