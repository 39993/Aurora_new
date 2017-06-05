package com.aorora.app.home.prezent.i;

import com.aorora.app.base.IBasePresenter;

/**
 * Created by Administrator on 2017/5/27.
 */
public interface IIntegePresenter extends IBasePresenter {

    void ongetdata(String nid,String startSize,String getCount,String keyword,String orderBy);


    void onMore(String nid,String startSize,String getCount,String keyword,String orderBy);

    void getBanner();

}
