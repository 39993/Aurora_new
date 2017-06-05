package com.aorora.app.cluster.prezent.iml;

import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.IndustrialClass;
import com.aorora.app.cluster.model.ClusterModle;
import com.aorora.app.cluster.model.i.IClusterModel;
import com.aorora.app.cluster.prezent.i.IClusterPresenter;
import com.aorora.app.cluster.prezent.listener.onClusterListener;
import com.aorora.app.cluster.view.IClusterView;

import java.util.List;

/**
 * author : Administrator on 2017/6/2.
 * time : 2017/06/02
 * fileNmae : ${fileName}
 * desc :
 */
public class ClusterPresenter implements IClusterPresenter,onClusterListener {

    private IClusterView view;
    private IClusterModel mModle;

    public ClusterPresenter(IClusterView view) {
        this.view = view;
        mModle = new ClusterModle(this);
    }

    @Override
    public void onSucess(List<IndustrialClass> data) {

        view.handleData(data);

    }

    @Override
    public void onFailed(String msg) {

        view.onError(msg);
    }

    @Override
    public void onBannerSucess(List<Advertisement> bannerlist) {

        view.getBanner(bannerlist);
    }

    @Override
    public void onBannerFailed(String msg) {

        view.onBannerError(msg);
    }



    //Activity调用
    @Override
    public void onDate() {

        mModle.getData();
    }

    @Override
    public void onBanner() {

        mModle.getBanner();
    }

    @Override
    public void onDestroy() {

        mModle.onDestroy();
    }
}
