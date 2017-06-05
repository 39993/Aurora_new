package com.aorora.app.home.model.i;

import com.aorora.app.base.IBaseModel;

/**
 * Created by Administrator on 2017/5/31.
 */
public interface IProductModel extends IBaseModel {

    void getInfo(String nid);

    void add(String count,String type,String id);
}
