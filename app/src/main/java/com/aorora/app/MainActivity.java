package com.aorora.app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.aorora.app.base.AppBaseActivity;
import com.aorora.app.tab.CenterFragmnet;
import com.aorora.app.tab.ClusterFragment;
import com.aorora.app.tab.HomeFragment;
import com.aorora.app.tab.IndustryFragment;
import com.aorora.app.tab.TabAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppBaseActivity {

    private TextView tv;
    private ViewPager main_viewpager;
    private TabLayout main_tablayout;
    private List<Fragment> list;
    private TabAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("互联网产业集群");

        initview();
        initTabLayout();
    }

    private void initTabLayout() {
        list = new ArrayList<>(4);

        HomeFragment hf = new HomeFragment();
        IndustryFragment inf = new IndustryFragment();
        ClusterFragment cf = new ClusterFragment();
        CenterFragmnet cef = new CenterFragmnet();
        list.add(hf);
        list.add(inf);
        list.add(cf);
        list.add(cef);

        main_viewpager.setOffscreenPageLimit(4);
        madapter = new TabAdapter(getSupportFragmentManager(),list,getApplicationContext());
        main_viewpager.setAdapter(madapter);

        //设置TabLayout的模式
        main_tablayout.setTabMode(TabLayout.MODE_FIXED);
        main_tablayout.setupWithViewPager(main_viewpager);
        for (int i = 0; i < main_tablayout.getTabCount(); i++) {
            TabLayout.Tab tab = main_tablayout.getTabAt(i);
            //为每个tab设置自定义视图，获取自定视图的方法写在Adapter里面
            //同样也可以直接写在Activity里面
            tab.setCustomView(madapter.getCustomView(i));
        }

    }

    private void initview() {

        main_tablayout = (TabLayout) findViewById(R.id.main_tablayout);
        main_viewpager = (ViewPager) findViewById(R.id.main_viewpager);

    }

}
