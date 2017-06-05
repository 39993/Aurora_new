package com.aorora.app.home.prezent.listener;

import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.GiftBean;
import com.aorora.app.bean.GiftTypyBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
public interface onGiftListener {


    void onError(String msg);

    void onSuccess(List<GiftBean> data, List<GiftTypyBean> type,String count);

    void MoreFailed(List<GiftBean> data,String msg);

    void LoadMore(List<GiftBean> data,String msg);


    void getBannerSucess(List<Advertisement> list);

    void getBannerFailed(String msg);

}
