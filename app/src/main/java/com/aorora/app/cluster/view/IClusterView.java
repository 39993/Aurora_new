package com.aorora.app.cluster.view;

import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.IndustrialClass;

import java.util.List;

/**
 * author : Administrator on 2017/6/2.
 * time : 2017/06/02
 * fileNmae : ${fileName}
 * desc :
 */
public interface IClusterView {

    void handleData(List<IndustrialClass> data);

    void onError(String msg);

    void getBanner(List<Advertisement> bannerlist);

    void onBannerError(String msg);

}
