package com.aorora.app.home.prezent.listener;

import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.InteBean;
import com.aorora.app.bean.TypeClass;

import java.util.List;

/**
 * Created by Administrator on 2017/5/27.
 */
public interface onInteListener {

    void onSuccess(List<InteBean> list,List<TypeClass> rules);

    void onFailed(String msg);


    void loadMore(List<InteBean> list);

    void moreFailed(String msg);

    void getBanner(List<Advertisement> list);

    void Bannerfailed(String msg);
}
