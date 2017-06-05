package com.aorora.app.home.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.InteBean;
import com.aorora.app.bean.ResponseClass;
import com.aorora.app.bean.ResponseIntegralClass;
import com.aorora.app.bean.TypeClass;
import com.aorora.app.home.model.i.IIntegelModel;
import com.aorora.app.home.prezent.listener.onInteListener;
import com.aorora.app.okgo.JsonCallback;
import com.aorora.app.uitls.ARLConfig;
import com.lzy.okgo.OkGo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/27.
 */
public class IntegelModle implements IIntegelModel{

    private onInteListener mListener;

    public IntegelModle(onInteListener listener) {
        this.mListener = listener;
    }


    @Override
    public void LoadMore(String nid, String startSize, String getCount, String keyword, String orderBy) {

        HashMap<String,String> map = new HashMap<>();
        map.put("nid",nid);
        map.put("startSize",startSize);
        map.put("getCount",getCount);
        map.put("keyword",keyword);
        map.put("orderBy",orderBy);

        load(map,true);

    }

    @Override
    public void getdata(String nid, String startSize, String getCount, String keyword, String orderBy) {

        HashMap<String,String> map = new HashMap<>();
        map.put("nid",nid);
        map.put("startSize",startSize);
        map.put("getCount",getCount);
        map.put("keyword",keyword);
        map.put("orderBy",orderBy);

        load(map,false);

    }

    @Override
    public void getBanner() {

        OkGo.post(ARLConfig.bannerList)
                .tag("inte")
                .params("keyword", "积分换购")
                .execute(new JsonCallback() {

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                        mListener.Bannerfailed("加载失败");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        ResponseClass responseClass = JSON.parseObject(
                                s, new TypeReference<ResponseClass>() {
                                });

                        String message = responseClass.ErrorMessage;
                        String data = responseClass.rows;
                        String state = responseClass.totalCount;

                        if (state != null && Integer.parseInt(state) > 0) {

                            List<Advertisement> adverts = JSON.parseObject(data,
                                    new TypeReference<List<Advertisement>>() {
                                    });

                            mListener.getBanner(adverts);
                        }else{

                            mListener.Bannerfailed("加载失败");
                        }

                    }
                });

    }


    private void load(Map<String,String> map, final Boolean isMore){

        OkGo.post(ARLConfig.productIntegralSearch)
                .tag("inte")
                .params(map)
                .execute(new JsonCallback() {

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                        mListener.onFailed("加载失败");

                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        Log.e("onSuccess积分换购",s);

                        ResponseIntegralClass responseClass = JSON
                                .parseObject(s,new TypeReference<ResponseIntegralClass>() {
                                });

                        String message = responseClass.getErrorMessage();// 错误信息
                        String data = responseClass.getRows();// 数据项
                        String state = responseClass.getTotalCount();// 数据状态
                        String propertys = responseClass.getProductType();//
                        String integralBalance = responseClass.getIntegralBalance();//

                        if(Integer.parseInt(state)>0){

                            List<InteBean> list = JSON.parseObject(data,
                                    new TypeReference<List<InteBean>>() {
                                    });// 列表数据

                            List<TypeClass> rules = JSON.parseObject(propertys,
                                    new TypeReference<List<TypeClass>>() {
                                    });//


                            if (isMore){

                                mListener.loadMore(list);
                            }else{

                                mListener.onSuccess(list,rules);
                            }

                        }else{

                            if(isMore){

                                mListener.moreFailed("没有更多了");

                            }else{

                                mListener.onFailed("加载失败");

                            }

                        }



                    }
                });



    }

    @Override
    public void onDestroy() {

        OkGo.getInstance().cancelTag("inte");
    }

}
