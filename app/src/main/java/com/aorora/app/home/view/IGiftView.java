package com.aorora.app.home.view;

import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.GiftBean;
import com.aorora.app.bean.GiftTypyBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
public interface IGiftView {

        //加载成功的方法
        void sucess(List<GiftBean> data,List<GiftTypyBean> type,String count);

        //失败的方法
        void failed(String msg);

        //加载更多的方法
        void loadMore(List<GiftBean> list,String msg);

        void MoreError(List<GiftBean> list,String msg);

        //刷新的方法
        void refresh(List<GiftBean> list);

        void handleBanner(List<Advertisement>list);

        void getBannerFailed(String msg);
}
