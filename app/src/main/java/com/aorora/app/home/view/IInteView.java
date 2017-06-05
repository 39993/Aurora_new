package com.aorora.app.home.view;

import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.InteBean;
import com.aorora.app.bean.TypeClass;

import java.util.List;

/**
 * Created by Administrator on 2017/5/27.
 */
public interface IInteView {


    void onGetdata(List<InteBean> data,List<TypeClass> rules);

    void onError(String msg);


    void getMore(List<InteBean> data);

    void FailedMore(String msg);


    void handleBanner(List<Advertisement> bannerlist);

    void getBannerFailed(String msg);
}
