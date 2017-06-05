package com.aorora.app.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aorora.app.R;
import com.aorora.app.adapter.MyAdapter;
import com.aorora.app.base.AppBaseActivity;
import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.GiftBean;
import com.aorora.app.bean.GiftTypyBean;
import com.aorora.app.home.prezent.i.IGiftPresenter;
import com.aorora.app.home.prezent.iml.GiftPrezenter;
import com.aorora.app.home.view.IGiftView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @文件名 GiftActivity.java
 * @注释 礼品中心
 */
public class GiftActivity extends AppBaseActivity implements View.OnClickListener ,IGiftView {

    private TextView classification;    //分类
    private TwinklingRefreshLayout gift_refresh;
    private EditText keyword;
    private Button serach;
    private IGiftPresenter mIGiftPresenter;
    private int startSize = 0;// 分页起始码
    private int getCount = 20;// 分页数据项
    private String keywordString = "";// 关键字
    private static final String orderBy = "";
    private String nid = "";// 分类ID
    private List<GiftTypyBean> typedata;

    private RecyclerView gift_cyclerView;
    private MyAdapter mMyAdapter;
    private AppBarLayout appbar_layout;
    private List<GiftBean> listdata;
    private TextView number_gift,activityrule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_gift);

        setDisplayHomeAsUpEnabled(true);
        setTitle("礼品中心");

        initview();

        initdata();

    }

    private void initview() {

        activityrule = (TextView) findViewById(R.id.activityrule);
        activityrule.setOnClickListener(this);
        number_gift = (TextView) findViewById(R.id.number_gift);

        gift_cyclerView = (RecyclerView) findViewById(R.id.gift_cyclerView);
        gift_cyclerView.setLayoutManager(new GridLayoutManager(GiftActivity.this,2));

        listdata = new ArrayList<>();
        typedata = new ArrayList<>();

        classification = (TextView) findViewById(R.id.classification);
        gift_refresh = (TwinklingRefreshLayout) findViewById(R.id.gift_refresh);
        gift_refresh.setEnableOverScroll(false);
        gift_refresh.setTargetView(gift_cyclerView);
        gift_refresh.setFloatRefresh(true);

        keyword = (EditText) findViewById(R.id.keyword);
        serach = (Button) findViewById(R.id.serach);

        classification.setOnClickListener(this);
        serach.setOnClickListener(this);
        keyword.setOnClickListener(this);

        appbar_layout = (AppBarLayout) findViewById(R.id.appbar_layout);

        appbar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    gift_refresh.setEnableRefresh(true);
                    gift_refresh.setEnableOverScroll(false);
                } else {
                    gift_refresh.setEnableRefresh(false);
                    gift_refresh.setEnableOverScroll(false);
                }
            }
        });

        gift_refresh.setOnRefreshListener(new RefreshListenerAdapter() {


            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);

                getMore();

            }

            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);

                getRefresh();

            }
        });

    }

    //刷新 TwinklingRefreshLayout
    private void getRefresh() {

         int startSize = 0;// 分页起始码
         int getCount = 20;// 分页数据项
         nid = "";
         keywordString = "";
        showLoading();
        mIGiftPresenter.getData(nid,startSize+"",getCount+"",keywordString,orderBy);

    }

    //加载更多 TwinklingRefreshLayout
    private void getMore() {

        startSize = startSize + getCount;
        showLoading();
        mIGiftPresenter.getLoad(nid,startSize+"",getCount+"",keywordString,orderBy);

    }

    private void initdata() {

        mIGiftPresenter = new GiftPrezenter(this);

        showLoading();
        mIGiftPresenter.getData(nid,startSize+"",getCount+"",keywordString,orderBy);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.classification: //分类  弹出分类对话框

                showShortToast("暂无分类");

                break;

            case R.id.serach: //搜索

                keywordString = keyword.getEditableText().toString().trim();

                showLoading();
                mIGiftPresenter.getData(nid,startSize+"",getCount+"",keywordString,orderBy);

                keyword.setText("");

                break;
            case R.id.activityrule:

                showShortToast("活动规则");

                break;

        }

    }


    @Override
    public void sucess(List<GiftBean> data,List<GiftTypyBean> type,String count) {

//        typedata.addAll(type); //分类数据         count可领奖次数

        number_gift.setText("当前可领奖次数："+count);
        setAdapter(listdata);

    }

    private void setAdapter(final List<GiftBean> data) {


        dismissLoading();
        mMyAdapter = new MyAdapter(data);

        mMyAdapter.notifyDataSetChanged();
        gift_cyclerView.setAdapter(mMyAdapter);

        //Item的点击事件
        mMyAdapter.setOnItemClickListener(new MyAdapter.MyItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {

                GiftBean bean = data.get(position);

                Intent inten = new Intent();
                inten.putExtra("product_Id", bean.productId);
                inten.putExtra("my", "gift");
                inten.setClass(getApplicationContext(), ProductInfoActivity.class);
                startActivity(inten);

                showShortToast(""+position);

            }
        });

    }

    @Override
    public void failed(String msg) {

        gift_refresh.finishRefreshing();
        dismissLoading();
        showShortToast(msg);

    }


    @Override
    public void loadMore(List<GiftBean> list,String msg) {


        listdata.addAll(list);
        setAdapter(listdata);
        gift_refresh.finishLoadmore();



    }

    @Override
    public void MoreError(List<GiftBean> list, String msg) {

        showShortToast("没有更多了");
        dismissLoading();
        gift_refresh.finishLoadmore();


    }

    @Override
    public void refresh(List<GiftBean> list) {

        setAdapter(list);
        gift_refresh.finishRefreshing();

    }

    /**
     * 获取轮播图数据
     */
    @Override
    public void handleBanner(List<Advertisement> list) {

        gift_refresh.finishRefreshing();
        dismissLoading();

    }

    @Override
    public void getBannerFailed(String msg) {

        gift_refresh.finishRefreshing();
        dismissLoading();
        showShortToast(msg);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭请求
        mIGiftPresenter.onDestroy();

    }
}
