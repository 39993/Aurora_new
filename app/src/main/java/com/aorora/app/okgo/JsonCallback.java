package com.aorora.app.okgo;

import android.util.Log;

import com.aorora.app.uitls.ARLConfig;
import com.aorora.app.uitls.AppPreference;
import com.aorora.app.uitls.Mybean;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.BaseRequest;

import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/24.
 */
public abstract class JsonCallback extends AbsCallback<String >{

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
        String url = AppPreference.getAppPreference().getString("url", "");
        String str = ARLConfig.key+url;
        String appSecret = Mybean.md5(str);

        if(""==url){
            request.params("memberId","")
                    .params("appSecret", "")
                    .params("appKey", ARLConfig.appKey);
            Log.e("全局参数", "appSecret: " + "空的");

        }else{
            request.params("memberId",AppPreference.getAppPreference().getString("id", ""))
                    .params("appSecret", appSecret)
                    .params("appKey", ARLConfig.appKey);

            Log.e("全局参数", "appSecret: " + appSecret);
        }


    }

    @Override
    public String convertSuccess(Response response) throws Exception {
        return response.body().string();
    }
}
