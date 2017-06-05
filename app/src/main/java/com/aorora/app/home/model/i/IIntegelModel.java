package com.aorora.app.home.model.i;

import com.aorora.app.base.IBaseModel;

/**
 * Created by Administrator on 2017/5/27.
 */
public interface IIntegelModel extends IBaseModel{

    void LoadMore(String nid, String startSize, String getCount, String keyword, String orderBy);

    void getdata(String nid, String startSize, String getCount, String keyword, String orderBy);

    void getBanner();

}
