package com.aorora.app.cluster.prezent.i;

import com.aorora.app.base.IBasePresenter;

/**
 * author : Administrator on 2017/6/3.
 * time : 2017/06/03
 * fileNmae : ${fileName}
 * desc :
 */
public interface IMemberPresenter extends IBasePresenter {

    void onGetData(String customerId,String startsize,String getCount,String keyword,String cosType);

    void onMore(String customerId,String startsize,String getCount,String keyword,String cosType);

}
