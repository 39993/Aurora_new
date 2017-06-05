package com.aorora.app.base;

import android.app.Application;

import com.aorora.app.uitls.AppPreference;
import com.lzy.okgo.OkGo;

/**
 * Created by Administrator on 2017/5/24.
 */
public class APP extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        AppPreference appPreference = AppPreference.init(this);

        //必须调用初始化
        OkGo.init(this);


    }


}
