package com.aorora.app.home.prezent.i;

import com.aorora.app.base.IBasePresenter;

/**
 * Created by Administrator on 2017/5/24.
 */
public interface IGiftPresenter extends IBasePresenter{

    void getData(String nid,String startSize,String getCount,String keyword,String orderBy);

    void getLoad(String nid,String startSize,String getCount,String keyword,String orderBy);

    void getBanner();


}
