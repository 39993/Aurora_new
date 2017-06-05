package com.aorora.app.tab;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aorora.app.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
public class TabAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;
    private Context context;
    private int [] tabImage = new int[]{R.drawable.custom_radio_home_btn,R.drawable.quality_color_selector,
                                R.drawable.industrial_color_selector,R.drawable.member_color_selector};

    private String[] titles = new String[]{"首页","产业支持","产业集群","会员中心"};
    public TabAdapter(FragmentManager fm, List<Fragment> list, Context context) {
        super(fm);
        this.list = list;
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size()>0?list.size():0;
    }

    public View getCustomView(int position){

        View view = LayoutInflater.from(context).inflate(R.layout.tab_custom_view,null);

        ImageView img = (ImageView) view.findViewById(R.id.tab_img);
        TextView txtview = (TextView) view.findViewById(R.id.tab_text);
        img.setImageResource(tabImage[position]);
        txtview.setText(titles[position]);

        if(0==position){
            view.setSelected(true);
        }

        return view;
    }


}
