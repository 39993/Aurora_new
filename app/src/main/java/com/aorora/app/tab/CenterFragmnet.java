package com.aorora.app.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aorora.app.R;
import com.aorora.app.adapter.MemberCentrAdapter;
import com.aorora.app.base.BaseFragment;
import com.aorora.app.membercenter.AccountActivity;
import com.aorora.app.widget.MyGridView;

/**
 * Created by Administrator on 2017/5/18.
 * 会员中心
 */
public class CenterFragmnet extends BaseFragment implements View.OnClickListener {

    private View view;
    private MyGridView membercenter_gridview;

    private static Integer icon [] = {R.mipmap.icon_my_order,R.mipmap.icon_my_cart_p,R.mipmap.icon_my_cart_c,R.mipmap.icon_my_cart_j,R.mipmap.icon_my_prize,
            R.mipmap.icon_my_redbag,R.mipmap.icon_my_cz,R.mipmap.icon_my_czgl,R.mipmap.icon_my_hy,
            R.mipmap.icon_my_hycx,R.mipmap.icon_my_save,R.mipmap.icon_my_wl,R.mipmap.icon_my_wlcx,
            R.mipmap.icon_my_set,R.mipmap.icon_my_shjfsq};

    private static String title [] = {"我的订单","品质商城购物车","产业商城购物车","积分商城购物车","中奖管理","我的红包","我要充值","充值管理","我的会员","会员查询",
            "我的收藏","订单物流","物流查询","系统设置","审核积分申请"};

    private TextView user_info_tv;

    public CenterFragmnet() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.center_fragment,container,false);

        initview();

        return view;
    }

    //初始化控件
    private void initview() {

        membercenter_gridview = (MyGridView) view.findViewById(R.id.membercenter_gridview);
        MemberCentrAdapter adapter = new MemberCentrAdapter(getContext(),icon,title);
        membercenter_gridview.setAdapter(adapter);

        user_info_tv = (TextView) view.findViewById(R.id.user_info_tv);

        user_info_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.user_info_tv:

                startActivity(new Intent(getContext(), AccountActivity.class));
                break;


        }

    }
}
