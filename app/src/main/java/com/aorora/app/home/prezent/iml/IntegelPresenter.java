package com.aorora.app.home.prezent.iml;

import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.InteBean;
import com.aorora.app.bean.TypeClass;
import com.aorora.app.home.model.IntegelModle;
import com.aorora.app.home.prezent.i.IIntegePresenter;
import com.aorora.app.home.prezent.listener.onInteListener;
import com.aorora.app.home.view.IInteView;

import java.util.List;

/**
 * Created by Administrator on 2017/5/27.
 */
public class IntegelPresenter implements IIntegePresenter,onInteListener {

    private IInteView view;
    private IntegelModle mModle;

    public IntegelPresenter(IInteView view) {
        this.view = view;
        mModle = new IntegelModle(this);
    }

    @Override
    public void ongetdata(String nid, String startSize, String getCount, String keyword, String orderBy) {

        mModle.getdata(nid,startSize,getCount,keyword,orderBy);

    }

    @Override
    public void onMore(String nid, String startSize, String getCount, String keyword, String orderBy) {

        mModle.LoadMore(nid,startSize,getCount,keyword,orderBy);

    }

    /**
     * 获取轮播图数据
     */
    @Override
    public void getBanner() {

        mModle.getBanner();
    }

    @Override
    public void onDestroy() {

        mModle.onDestroy();

    }


    @Override
    public void onSuccess(List<InteBean> list,List<TypeClass> rules) {

        view.onGetdata(list,rules);

    }

    @Override
    public void onFailed(String msg) {

        view.onError(msg);

    }

    @Override
    public void loadMore(List<InteBean> list) {

        view.getMore(list);

    }

    @Override
    public void moreFailed(String msg) {

        view.FailedMore(msg);

    }

    @Override
    public void getBanner(List<Advertisement> list) {

        view.handleBanner(list);

    }

    @Override
    public void Bannerfailed(String msg) {

        view.getBannerFailed(msg);
    }


}
