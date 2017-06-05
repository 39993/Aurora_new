package com.aorora.app.cluster.model.i;

import com.aorora.app.base.IBaseModel;

/**
 * author : Administrator on 2017/6/3.
 * time : 2017/06/03
 * fileNmae : ${fileName}
 * desc :
 */
public interface IMemberModel extends IBaseModel {

    void requster(String customerId,String startsize,String getCount,String keyword,String cosType);

    void More(String customerId,String startsize,String getCount,String keyword,String cosType);

}
