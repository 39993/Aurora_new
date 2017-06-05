package com.aorora.app.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aorora.app.R;
import com.aorora.app.banner.Banner;
import com.aorora.app.banner.loader.GlideImageLoader;
import com.aorora.app.base.BaseFragment;
import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.ResponseClass;
import com.aorora.app.home.GiftActivity;
import com.aorora.app.home.IntegelActivity;
import com.aorora.app.home.WebViewActivity;
import com.aorora.app.okgo.JsonCallback;
import com.aorora.app.uitls.ARLConfig;
import com.aorora.app.uitls.AppPreference;
import com.aorora.app.widget.CustomPop;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/18.
 * 首页
 */
public class HomeFragment extends BaseFragment {


    @Bind(R.id.banner)
    Banner mBanner;
    @Bind(R.id.productdisplay)
    LinearLayout mProductdisplay;
    @Bind(R.id.mySignin)
    LinearLayout mMySignin;
    @Bind(R.id.promotionpictures)
    LinearLayout mPromotionpictures;
    @Bind(R.id.invitefriends)
    LinearLayout mInvitefriends;
    @Bind(R.id.myredpackets)
    LinearLayout mMyredpackets;
    @Bind(R.id.throwingeggs)
    ImageView mThrowingeggs;
    @Bind(R.id.contactcustomerservice)
    LinearLayout mContactcustomerservice;
    @Bind(R.id.membercenter)
    LinearLayout mMembercenter;
    @Bind(R.id.integralfor)
    LinearLayout mIntegralfor;
    @Bind(R.id.giftcenter)
    LinearLayout mGiftcenter;
    @Bind(R.id.helpcenter)
    LinearLayout mHelpcenter;

    private View view;

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this,view);

        initdata();

        return view;
    }

    //加载数据 首页广告图
    private void initdata() {

        OkGo.post(ARLConfig.bannerList)
                .tag("home")
                .params("keyword","首页")
                .execute(new JsonCallback() {

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showShortToast("加载失败");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        ResponseClass responseClass = JSON.parseObject(
                                s, new TypeReference<ResponseClass>() {
                                });

                        String message = responseClass.ErrorMessage;// 错误信息
                        String data = responseClass.rows;// 数据
                        String state = responseClass.totalCount;// 数据状态

                        if (state != null && Integer.parseInt(state) > 0) {

                            List<Advertisement> list = JSON.parseObject(data,
                                    new TypeReference<List<Advertisement>>() {
                                    });

                            List<String> str = new ArrayList<String>();
                            for (int i = 0; i <list.size() ; i++) {

                                str.add(ARLConfig.URL+list.get(i).getPicture());
                            }

                            if(list.size()==1){
                                str.add(ARLConfig.URL+list.get(0).getPicture());
                            }

                            mBanner.setImages(str)
                                    .setImageLoader(new GlideImageLoader())
                                    .start();

                        }else{

                            showShortToast("加载失败");
                            return;
                        }

                    }
                });


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag("home");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.productdisplay, R.id.mySignin, R.id.promotionpictures, R.id.invitefriends, R.id.myredpackets, R.id.throwingeggs, R.id.contactcustomerservice, R.id.membercenter, R.id.integralfor, R.id.giftcenter, R.id.helpcenter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.productdisplay:  //产品展示


                break;
            case R.id.mySignin:   //我要签到

                String url = ARLConfig.daySign + "?mtoken="
                        + AppPreference.getAppPreference().getString("url", "");
                Intent intent = new Intent();
                intent.putExtra("url", url);
                intent.putExtra("title", "签到");
                intent.setClass(getActivity(), WebViewActivity.class);
                startActivity(intent);


                break;
            case R.id.promotionpictures: //推广图片



                break;
            case R.id.invitefriends:  //邀请好友


                break;
            case R.id.myredpackets:   //我的红包

                String urlbg = ARLConfig.redPapert + "?mtoken="
                        + AppPreference.getAppPreference().getString("url", "");
                Intent intentbg = new Intent();
                intentbg.putExtra("url", urlbg);
                intentbg.putExtra("title", "我的红包");
                intentbg.setClass(getActivity(), WebViewActivity.class);
                startActivity(intentbg);

                break;
            case R.id.throwingeggs:  //免费砸金蛋

                String urleggs = ARLConfig.lottery + "?mtoken="
                        + AppPreference.getAppPreference().getString("url", "");
                Intent intenteggs = new Intent();
                intenteggs.putExtra("url", urleggs);
                intenteggs.putExtra("title", "免费砸金蛋");
                intenteggs.setClass(getActivity(), WebViewActivity.class);
                startActivity(intenteggs);


                break;
            case R.id.contactcustomerservice: //联系客服


                CustomPop  pop = new CustomPop(getActivity());

                pop.showAtLocation(view, Gravity.CENTER,0,0);

                break;


            case R.id.membercenter:  //驰嘉汇民


                break;


            case R.id.integralfor://积分换购

                startActivity(new Intent(getActivity(), IntegelActivity.class));
                break;


            case R.id.giftcenter://礼品中心

                startActivity(new Intent(getActivity(), GiftActivity.class));

                break;

            case R.id.helpcenter: //帮助



                break;
        }
    }
}
