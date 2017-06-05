package com.aorora.app.login.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aorora.app.bean.ResponseClass;
import com.aorora.app.bean.User;
import com.aorora.app.login.model.i.ILoginModel;
import com.aorora.app.login.presenter.listener.onLoginListener;
import com.aorora.app.okgo.JsonCallback;
import com.aorora.app.uitls.ARLConfig;
import com.aorora.app.uitls.AppPreference;
import com.lzy.okgo.OkGo;

import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/25.
 */
public class LoginModel implements ILoginModel {

    private static final String TAG = "Login_M";

    private onLoginListener mOnLoginListener;

    public LoginModel(onLoginListener onLoginListener) {
        mOnLoginListener = onLoginListener;

    }


    @Override
    public void login(String userName,String password) {

        Log.e(TAG, "login: " +userName +"..."+ password);

        HashMap<String,String> map = new HashMap<>();
        map.put("password",password);
        map.put("number",userName);

        OkGo.post(ARLConfig.login)
                .params(map)
                .execute(new JsonCallback() {

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                        Log.e(TAG,"onError: "+e);

                        mOnLoginListener.loginFailed("登录失败"+e);

                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        Log.e(TAG, "onSuccess: " + s);



                        ResponseClass responseClass = JSON.parseObject(s,
                                new TypeReference<ResponseClass>() {
                                });
                        String message = responseClass.ErrorMessage;
                        String data = responseClass.rows;
                        String state = responseClass.totalCount;
                        String error = responseClass.error;


                        if(null==data){

                            mOnLoginListener.reason(error);

                        }else{

                        List<User> list=null;
                        list = JSON.parseObject(data,
                                new TypeReference<List<User>>() {
                                });
                        User menber=list.get(0);

                        AppPreference.getAppPreference().putString("url", menber.url);
                        AppPreference.getAppPreference().putString("id", menber.id);
                        AppPreference.getAppPreference().putString("number", menber.number);
                        AppPreference.getAppPreference().putString("bankAccountName", menber.bankAccountName);
                        AppPreference.getAppPreference().putString("bankName", menber.bankName);
                        AppPreference.getAppPreference().putString("bankAccount", menber.bankAccount);
                        AppPreference.getAppPreference().putString("bankAddressProvince", menber.bankAddressProvince);
                        AppPreference.getAppPreference().putString("bankAddressCity", menber.bankAddressCity);
                        AppPreference.getAppPreference().putString("bankAddressHometown", menber.bankAddressHometown);
                        AppPreference.getAppPreference().putString("bankAddress", menber.bankAddress);
                        AppPreference.getAppPreference().putString("expressUrl", menber.expressUrl);
                        AppPreference.getAppPreference().putString("shareUrl", menber.shareUrl);
                        AppPreference.getAppPreference().putString("weixinPicture", menber.weixinPicture);
                        AppPreference.getAppPreference().putString("appPicture", menber.appPicture);

                        mOnLoginListener.loginSuccess(menber);

                        }

                    }

                });

    }


}
