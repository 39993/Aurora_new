package com.aorora.app.home.prezent.iml;

import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.GiftBean;
import com.aorora.app.bean.GiftTypyBean;
import com.aorora.app.home.model.GiftModle;
import com.aorora.app.home.model.i.IGiftModel;
import com.aorora.app.home.prezent.i.IGiftPresenter;
import com.aorora.app.home.prezent.listener.onGiftListener;
import com.aorora.app.home.view.IGiftView;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
public class GiftPrezenter implements IGiftPresenter,onGiftListener {


    private IGiftModel mModle;
    private IGiftView view;

    public GiftPrezenter(IGiftView view) {
        this.view = view;
        mModle = new GiftModle(this);
    }

    @Override
    public void getData(String nid, String startSize, String getCount, String keyword, String orderBy) {

        mModle.getdata(nid,startSize,getCount,keyword,orderBy);
    }

    //加载更多
    @Override
    public void getLoad(String nid, String startSize, String getCount, String keyword, String orderBy) {

        mModle.LoadMore(nid,startSize,getCount,keyword,orderBy);

    }

    @Override
    public void getBanner() {

        mModle.getBanner();
    }





    @Override
    public void onDestroy() {

        mModle.onDestroy();

    }


    @Override
    public void onError(String msg) {

        view.failed(msg);

    }

    @Override
    public void onSuccess(List<GiftBean> data,List<GiftTypyBean> type,String count) {

        view.sucess(data,type, count);
        view.refresh(data);

    }

    //加载更多失败
    @Override
    public void MoreFailed(List<GiftBean> data, String msg) {

        view.MoreError(data,msg);
    }

    @Override
    public void LoadMore(List<GiftBean> data,String msg) {

        view.loadMore(data,msg);

    }

    @Override
    public void getBannerSucess(List<Advertisement> list) {

        view.handleBanner(list);

    }

    @Override
    public void getBannerFailed(String msg) {

        view.getBannerFailed(msg);

    }


}
