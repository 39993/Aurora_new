package com.aorora.app.tab;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aorora.app.R;
import com.aorora.app.base.BaseFragment;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

/**
 * Created by Administrator on 2017/5/18.
 * 产业支持
 */
public class IndustryFragment extends BaseFragment {


    private TwinklingRefreshLayout refresh;

    public IndustryFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_industry,container,false);

        initview(view);

        return view;
    }

    private void initview(View view) {

        refresh = (TwinklingRefreshLayout)view.findViewById(R.id.refresh);
        refresh.setEnableLoadmore(false);

        refresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        refreshLayout.finishRefreshing();
                    }
                },2000);


            }
        });


    }



}
