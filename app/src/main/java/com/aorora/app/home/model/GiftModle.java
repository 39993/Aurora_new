package com.aorora.app.home.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.GiftBean;
import com.aorora.app.bean.GiftTypyBean;
import com.aorora.app.bean.ResponseClass;
import com.aorora.app.bean.ResponseTypeClass;
import com.aorora.app.home.model.i.IGiftModel;
import com.aorora.app.home.prezent.listener.onGiftListener;
import com.aorora.app.okgo.JsonCallback;
import com.aorora.app.uitls.ARLConfig;
import com.lzy.okgo.OkGo;

import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/24.
 */
public class GiftModle implements IGiftModel{


    private onGiftListener mListener;

    public GiftModle(onGiftListener listener) {
        mListener = listener;
    }


    @Override
    public void getdata(String nid, String startSize, String getCount, String keyword, String orderBy) {

        HashMap<String,String> map = new HashMap<>();

            map.put("nid",nid);
            map.put("startSize",startSize);
            map.put("getCount",getCount);
            map.put("keyword",keyword);
            map.put("orderBy",orderBy);

            load(map,true);
    }

    @Override
    public void getBanner() {

        OkGo.post(ARLConfig.bannerList)
                .params("keyword", "礼品中心") // 关键字
                .execute(new JsonCallback() {

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                        mListener.getBannerFailed("加载失败");

                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        ResponseClass responseClass = JSON.parseObject(
                                s, new TypeReference<ResponseClass>() {
                                });

                        String message = responseClass.ErrorMessage;// 错误信息
                        String data = responseClass.rows;// 数据
                        String state = responseClass.totalCount;// 数据状态

                        if (state != null && Integer.parseInt(state) > 0) {

                            List<Advertisement> adverts = JSON.parseObject(data,
                                    new TypeReference<List<Advertisement>>() {
                                    });

                            mListener.getBannerSucess(adverts);

                        }else{

                            mListener.getBannerFailed("加载失败");

                        }

                    }

                });

    }


    //加载更多
    @Override
    public void LoadMore(String nid, String startSize, String getCount, String keyword, String orderBy) {

        HashMap<String,String> map = new HashMap<>();

        map.put("nid",nid);
        map.put("startSize",startSize);
        map.put("getCount",getCount);
        map.put("keyword",keyword);
        map.put("orderBy",orderBy);

        load(map,false);

    }

    @Override
    public void refresh() {

        
    }

    private void load(HashMap<String,String> map, final Boolean ismore){

        OkGo.post(ARLConfig.freeActivity)
                .tag("gift")
                .params(map)
                .execute(new JsonCallback() {

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                        mListener.onError("加载失败");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        Log.e("gift_m", "onSuccess: "+s );

                        ResponseTypeClass responseClass = JSON.parseObject(
                                s, new TypeReference<ResponseTypeClass>() {
                                });

                        String message = responseClass.ErrorMessage;// 错误信息
                        String data = responseClass.rows;// 数据
                        String totalCount = responseClass.totalCount;// 数据状态
                        String propertys = responseClass.productType;// 分类数据
                        String unUsedCount = responseClass.unUsedCount; //领取次数


                        if(Integer.parseInt(totalCount)>0){

                            Log.e("gift_m", "data: "+ data.toString() );

                            List<GiftBean>  Originallylist = JSON.parseObject(data,
                                    new TypeReference<List<GiftBean>>() {
                                    });// 列表

                            List<GiftTypyBean> rules = JSON.parseObject(propertys,
                                    new TypeReference<List<GiftTypyBean>>() {
                                    });// 规格

                            if(ismore){

                                mListener.onSuccess(Originallylist,rules,unUsedCount);
                            }else{

                                mListener.LoadMore(Originallylist,"加载成功");
                            }


                        }else{

                            if(ismore){
                                mListener.onError(message);
                            }else{
                                mListener.MoreFailed(null,"没有更多了");
                            }


                        }


                    }

                });

    }


    @Override
    public void onDestroy() {

        OkGo.getInstance().cancelTag("gift");

    }

}
