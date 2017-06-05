package com.aorora.app.home.prezent.i;

import com.aorora.app.base.IBasePresenter;

/**
 * Created by Administrator on 2017/5/31.
 */
public interface IProductPresenter extends IBasePresenter {

    void getData(String nid);

    void addCart(String count,String type,String id);

}
