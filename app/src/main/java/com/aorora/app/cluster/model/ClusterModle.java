package com.aorora.app.cluster.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.IndustrialClass;
import com.aorora.app.bean.ResponseClass;
import com.aorora.app.cluster.model.i.IClusterModel;
import com.aorora.app.cluster.prezent.listener.onClusterListener;
import com.aorora.app.okgo.JsonCallback;
import com.aorora.app.uitls.ARLConfig;
import com.lzy.okgo.OkGo;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * author : Administrator on 2017/6/1.
 * time : 2017/06/01
 * fileNmae : ${fileName}
 * desc : 产业集群主页的Modle
 */
public class ClusterModle implements IClusterModel {


    private onClusterListener mListener;

    public ClusterModle(onClusterListener listener) {
        mListener = listener;
    }

    //获取产业集群主页数据
    @Override
    public void getData() {


        OkGo.post(ARLConfig.industrialdetail)
                .tag("clu")
                .execute(new JsonCallback() {

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                        mListener.onFailed("加载失败");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        Log.e("产业集群", "onSuccess: "+s );

                        ResponseClass responseClass = JSON.parseObject(
                                s, new TypeReference<ResponseClass>() {
                                });

                        String message = responseClass.ErrorMessage;// 错误信息
                        String data = responseClass.rows;// 数据
                        String state = responseClass.totalCount;// 数据状态

                        if (state != null && Integer.parseInt(state) > 0) {

                            List<IndustrialClass> list = JSON.parseObject(data,
                                    new TypeReference<List<IndustrialClass>>() {
                                    });

                            mListener.onSucess(list);

                        }else{


                            mListener.onFailed("加载失败");

                        }


                    }
                });

    }

    //获取轮播图片地址
    @Override
    public void getBanner() {

        OkGo.post(ARLConfig.bannerList)
                .tag("clu")
                .params("keyword","产业集群")
                .execute(new JsonCallback() {

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                        mListener.onBannerFailed("广告图加载失败");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {


                        Log.e("产业集群广告图", "onSuccess: "+s );

                        ResponseClass responseClass = JSON.parseObject(
                                s, new TypeReference<ResponseClass>() {
                                });

                        String message = responseClass.ErrorMessage;// 错误信息
                        String data = responseClass.rows;// 数据
                        String state = responseClass.totalCount;// 数据状态

                        JSONObject obj = new JSONObject();

                        if (state != null && Integer.parseInt(state) > 0) {

                            List<Advertisement> list = obj.parseObject(data,
                                    new TypeReference<List<Advertisement>>() {
                                    });

                            mListener.onBannerSucess(list);

                        }else{

                            mListener.onBannerFailed("广告图加载失败");

                        }


                    }
                });

    }



    @Override
    public void onDestroy() {

        OkGo.getInstance().cancelTag("clu");

    }

}
