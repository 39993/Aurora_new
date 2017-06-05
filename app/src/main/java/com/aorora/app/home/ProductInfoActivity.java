package com.aorora.app.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aorora.app.R;
import com.aorora.app.adapter.PhotoAdapter;
import com.aorora.app.banner.Banner;
import com.aorora.app.banner.listenner.OnBannerListener;
import com.aorora.app.banner.loader.GlideImageLoader;
import com.aorora.app.base.AppBaseActivity;
import com.aorora.app.bean.GoodsDetaill;
import com.aorora.app.bean.PicturesClass;
import com.aorora.app.bean.Rule;
import com.aorora.app.home.prezent.i.IProductPresenter;
import com.aorora.app.home.prezent.iml.ProductPresenter;
import com.aorora.app.home.view.IProductView;
import com.aorora.app.uitls.ARLConfig;
import com.aorora.app.widget.MyListView;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator
 * @文件名 ProductInfoActivity.java
 * @注释 产品详情页 （礼品中心 积分换购）
 */


public class ProductInfoActivity extends AppBaseActivity implements IProductView {

    @Bind(R.id.kefu)
    TextView mKefu;
    @Bind(R.id.addcart)
    TextView mAddcart;
    @Bind(R.id.share)
    TextView mShare;
    @Bind(R.id.buy)
    TextView mBuy;
    @Bind(R.id.linear_bottom)
    LinearLayout mLinearBottom;
    @Bind(R.id.info_banner)
    Banner mInfoBanner;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.price)
    TextView mPrice;
    @Bind(R.id.marketPrice)
    TextView mMarketPrice;
    @Bind(R.id.discount)
    TextView mDiscount;
    @Bind(R.id.stock)
    TextView mStock;
    @Bind(R.id.postage)
    TextView mPostage;
    @Bind(R.id.reduce)
    TextView mReduce;
    @Bind(R.id.count)
    TextView mCount;
    @Bind(R.id.add)
    TextView mAdd;
    @Bind(R.id.photo_listview)
    MyListView mPhotoListview;
    @Bind(R.id.webview)
    WebView mWebview;

    private IProductPresenter iproductpresenter;
    private String productId;
    private String my;
    private int counts = 1;
    private static final String URL_IMAGE = ARLConfig.IMAGEURL;
    private GoodsDetaill goodsDetaill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        ButterKnife.bind(this);

        setTitle("产品详情");
        setDisplayHomeAsUpEnabled(true);
        productId = getIntent().getStringExtra("product_Id");
        my = getIntent().getStringExtra("my");
        initdata();

    }

    /**
    *
    * @author : Administrator
    * @time : 2017/6/1  11:00
    * @param : 初始化数据
    */

    private void initdata() {

        iproductpresenter = new ProductPresenter(this);

        if(null!=productId){

            showLoading();
            iproductpresenter.getData(productId);
        }


    }


    /**
    *
    * @author : Administrator
    * @time : 2017/6/1  11:00
    * @param : 控件点击事件
    */
    @OnClick({R.id.kefu,R.id.addcart, R.id.share, R.id.buy, R.id.reduce, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.kefu:  //客服

                break;

            case R.id.addcart: //查看购物车


                break;
            case R.id.share:  //分享


                break;
            case R.id.buy:  //加入购物车

                String count = mCount.getText().toString();
                String id = "";
                if(my.equals("gift")){

                    Log.e("礼品中心", "22");
                    id = goodsDetaill.id;// 产品ID

                }else if (my.equals("integral")){

                    Log.e("积分换购", "111");
                    id = goodsDetaill.articleId;// 产品ID
                }

                showLoading();
                iproductpresenter.addCart(count,my,id);

                break;
            case R.id.reduce:   //减


                if(counts>1){
                    counts--;
                    mCount.setText(counts+"");
                }else{
                    counts = 1;
                    mCount.setText(counts+"");
                }


                break;

            case R.id.add:    //加

                counts++;
                mCount.setText(counts+"");

                break;
        }
    }



    @Override
    public void Error(String msg) {

        dismissLoading();
        showShortToast(msg);

    }

    //请求返回的数据
    @Override
    public void getData(List<GoodsDetaill> list, List<PicturesClass> path, List<Rule> rules) {

        dismissLoading();
        if(null!=list){
            goodsDetaill  = list.get(0);
            updataUI(goodsDetaill);
        }

        if(null!=path){

            PhotoAdapter adapter = new PhotoAdapter(path,ProductInfoActivity.this);
            mPhotoListview.setAdapter(adapter);
            List<String> str = new ArrayList<>();
            for (int i = 0; i <path.size() ; i++) {
                str.add(URL_IMAGE+path.get(i).path);
            }

            mInfoBanner.setImages(str)
                    .setImageLoader(new GlideImageLoader())
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {


                        }
                    })
                    .start();




        }


    }

    /**
    *
    * @author : Administrator
    * @time : 2017/6/1  11:15
    * @param :请求到数据跟新UI
    */
    private void updataUI(GoodsDetaill goodsDetaill) {


        mStock.setText(goodsDetaill.stock+"分");
        mDiscount.setText("剩余库存：" + goodsDetaill.discount + "件");
        mMarketPrice.setText("￥" + goodsDetaill.marketPrice);
        mPrice.setText("￥" + goodsDetaill.price);
        mName.setText("" + goodsDetaill.name);
        mPostage.setText("邮费:￥" + 0.0);
        String url = URLDecoder.decode(goodsDetaill.content);

        mWebview.getSettings().setJavaScriptEnabled(false);
        mWebview.setWebViewClient(new SampleWebViewClient());
        mWebview.getSettings().setUseWideViewPort(false);
        mWebview.getSettings().setLoadWithOverviewMode(false);
        mWebview.getSettings().setAllowFileAccess(false);
        mWebview.getSettings().setDomStorageEnabled(false);//
        mWebview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebview.loadDataWithBaseURL(null, url, "text/html", "UTF-8", null);

    }


    private class SampleWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    //加入购物车成功
    @Override
    public void addResult(String msg,String type) {

        dismissLoading();
        showShortToast("加入成功"+msg);

    }

    //添加失败
    @Override
    public void addFailed(String msg) {

        dismissLoading();
        showShortToast(msg);

    }


    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        mInfoBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        mInfoBanner.stopAutoPlay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        iproductpresenter.onDestroy();
    }
}
