package com.aorora.app.home.model.i;

import com.aorora.app.base.IBaseModel;

/**
 * Created by Administrator on 2017/5/24.
 */
public interface IGiftModel extends IBaseModel {


    void refresh();


    void LoadMore(String nid, String startSize, String getCount, String keyword, String orderBy);

    void getdata(String nid, String startSize, String getCount, String keyword, String orderBy);

    void getBanner();

}
