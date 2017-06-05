package com.aorora.app.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aorora.app.R;
import com.aorora.app.adapter.InteAdapter;
import com.aorora.app.banner.Banner;
import com.aorora.app.banner.loader.GlideImageLoader;
import com.aorora.app.base.AppBaseActivity;
import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.InteBean;
import com.aorora.app.bean.TypeClass;
import com.aorora.app.home.prezent.i.IIntegePresenter;
import com.aorora.app.home.prezent.iml.IntegelPresenter;
import com.aorora.app.home.view.IInteView;
import com.aorora.app.uitls.ARLConfig;
import com.aorora.app.uitls.MyUtils;
import com.aorora.app.widget.TypeWindow;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator
 * @文件名 GiftActivity.java
 * @注释 积分换购
 */
public class IntegelActivity extends AppBaseActivity implements IInteView{


    @Bind(R.id.inte_type_tv)
    TextView mInteTypeTv;
    @Bind(R.id.inte_keyword_et)
    EditText mInteKeywordEt;
    @Bind(R.id.inte_serach_btn)
    Button mInteSerachBtn;
    @Bind(R.id.inte_banner)
    Banner mInteBanner;
    @Bind(R.id.number_inte)
    TextView mNumberInte;
    @Bind(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @Bind(R.id.inte_cyclerView)
    RecyclerView mInteCyclerView;
    @Bind(R.id.inte_refresh)
    TwinklingRefreshLayout mInteRefresh;

    private int startSize = 0;
    private int getCount = 20;
    private  String keywordString = "";
    private static final String orderBy = "";
    private String nid = "";
    private IIntegePresenter inpresenter;
    private InteAdapter adapter;
    private List<TypeClass> typerules;
    private TypeWindow mTypeWindow;
    private Boolean isAdd = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integel);
        ButterKnife.bind(this);

        setDisplayHomeAsUpEnabled(true);
        setTitle("积分换购");

        initview();
        initdata();

    }

    //配置控件属性
    private void initview() {

        typerules = new ArrayList<>();

        adapter = new InteAdapter();

        mInteCyclerView.setLayoutManager(new GridLayoutManager(IntegelActivity.this,2));
        mInteCyclerView.setAdapter(adapter);

        mInteRefresh.setEnableOverScroll(false);
        mInteRefresh.setTargetView(mInteCyclerView);
        mInteRefresh.setFloatRefresh(true);

        mInteRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);

                showLoading();
                int startSize = 0;
                int getCount = 20;
                nid = "";
                keywordString = "";
//                inpresenter.ongetdata(nid,startSize+"",getCount+"",keywordString,orderBy);
                initdata();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);

                loadMore();

            }
        });
        //设置AppBar的滑动监听
        MyUtils.setAppBar(mAppbarLayout,mInteRefresh);


    }

    private void loadMore() {

        showLoading();
        startSize = startSize + getCount;
        inpresenter.onMore(nid,startSize+"",getCount+"",keywordString,orderBy);

    }

    private void initdata() {

        showLoading();
        inpresenter = new IntegelPresenter(this);
        inpresenter.getBanner();
        inpresenter.ongetdata(nid,startSize+"",getCount+"",keywordString,orderBy);

        /**
         * Item的点击事件
         */
        adapter.setOnItemClickListener(new InteAdapter.MyItemClickListener() {
            @Override
            public void ItemClickListener(View view,int position) {

                InteBean bean = adapter.getDataList().get(position);

                Intent intent = new Intent();
                intent.putExtra("product_Id", bean.productId);
                Log.e("id", bean.productId);
                intent.putExtra("my", "integral");
                intent.setClass(IntegelActivity.this, ProductInfoActivity.class);
                startActivity(intent);

                showShortToast(""+position);

            }
        });


    }

    @OnClick({R.id.inte_type_tv, R.id.inte_serach_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inte_type_tv:  //分类  mInteTypeTv

                if(null!=typerules){
                    if(null==mTypeWindow){
                        mTypeWindow = new TypeWindow(this,typerules,mLisetener);
                    }
                    WindowManager.LayoutParams lp =this.getWindow().getAttributes();
                    lp.alpha = 0.5f;
                    this.getWindow().setAttributes(lp);
                    mTypeWindow.showAsDropDown(mInteTypeTv);
                }else{

                    showShortToast("暂无分类");
                }

                break;
            case R.id.inte_serach_btn:  //所搜

                keywordString = mInteKeywordEt.getEditableText().toString().trim();
                if(keywordString.equals("")){
                    showShortToast("请输入搜索内容");
                    return;
                }

                showLoading();
                int startSize = 0;
                int getCount = 20;
                nid = "";
                inpresenter.ongetdata(nid,startSize+"",getCount+"",keywordString,orderBy);
                mInteKeywordEt.setText("");

                break;
        }
    }

    /**
    *
    * @author : Administrator
    * @time : 2017/6/2  15:26
    * @param : 分类的监听
    */
    private TypeWindow.MyTypeClickLisetener mLisetener = new TypeWindow.MyTypeClickLisetener() {
        @Override
        public void ItemClicklistener(View view, int position) {

            showLoading();
            int startSize = 0;
            int getCount = 20;
            nid = typerules.get(position).nid;
            keywordString = "";
            inpresenter.ongetdata(nid,startSize+"",getCount+"",keywordString,orderBy);
            mTypeWindow.dismissPopupWindow();

        }
    };


    @Override
    public void onGetdata(List<InteBean> data,List<TypeClass> rules) {

        if(null!=rules){

            typerules.addAll(rules);
        }

        adapter.setDataList(data);
        dismissLoading();
        mInteRefresh.finishRefreshing();
    }

    @Override
    public void onError(String msg) {

        showShortToast(msg);
        dismissLoading();
        mInteRefresh.finishRefreshing();
    }

    @Override
    public void getMore(List<InteBean> data) {

        dismissLoading();
        adapter.addItems(data);
        mInteRefresh.finishLoadmore();
    }

    @Override
    public void FailedMore(String msg) {

        showShortToast(msg);
        dismissLoading();
        mInteRefresh.finishLoadmore();
    }

    /**
     * 轮播图数据
     * @param bannerlist
     */
    @Override
    public void handleBanner(List<Advertisement> bannerlist) {

        dismissLoading();
        mInteRefresh.finishRefreshing();

        if(null!=bannerlist){

            List<String> str = new ArrayList<>();
            for (int i = 0; i <bannerlist.size() ; i++) {
                str.add(ARLConfig.URL+bannerlist.get(i).getPicture());
            }

            if (bannerlist.size()==1) {
                str.add(ARLConfig.URL+bannerlist.get(0).getPicture());
            }

            mInteBanner.setImages(str)
                    .setImageLoader(new GlideImageLoader())
                    .start();

        }


    }

    @Override
    public void getBannerFailed(String msg) {

        dismissLoading();
        mInteRefresh.finishRefreshing();
        showShortToast("加载失败");

    }


}
