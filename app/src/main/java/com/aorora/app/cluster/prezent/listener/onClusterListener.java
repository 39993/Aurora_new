package com.aorora.app.cluster.prezent.listener;

import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.IndustrialClass;

import java.util.List;

/**
 * author : Administrator on 2017/6/2.
 * time : 2017/06/02
 * fileNmae : ${fileName}
 * desc :
 */
public interface onClusterListener {

    void onSucess(List<IndustrialClass> data);

    void onFailed(String msg);

    void onBannerSucess(List<Advertisement> bannerlist);

    void onBannerFailed(String msg);


}
