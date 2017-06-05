package com.aorora.app.home.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aorora.app.bean.GoodsDetaill;
import com.aorora.app.bean.PicturesClass;
import com.aorora.app.bean.ResponseDetailClass;
import com.aorora.app.bean.ResponseListClass;
import com.aorora.app.bean.Rule;
import com.aorora.app.home.model.i.IProductModel;
import com.aorora.app.home.prezent.listener.onProductListener;
import com.aorora.app.okgo.JsonCallback;
import com.aorora.app.uitls.ARLConfig;
import com.lzy.okgo.OkGo;

import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/31.
 */
public class ProductModle implements IProductModel {

    private onProductListener mOnProductListener;


    public ProductModle(onProductListener onProductListener) {
        mOnProductListener = onProductListener;
    }


    private void add(HashMap<String,String> map , final String type, String url){


        OkGo.post(url)
                .tag("info")
                .params(map)
                .execute(new JsonCallback() {

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                        mOnProductListener.addFailded("添加失败");

                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        Log.e("加入购物车", "onSuccess: "+s +".......type:"+type);

                        ResponseListClass responseClass = JSON.parseObject(
                                s, new TypeReference<ResponseListClass>() {
                                });

                        String message = responseClass.getError();// 错误信息
                        String data = responseClass.getData();// 数据项
                        String state = responseClass.getCount();// 数据状态

                        if (state != null && Integer.parseInt(state) > 0) {

                            mOnProductListener.Cart(message,type);

                        }else{

                            mOnProductListener.addFailded("添加失败");
                        }


                    }
                });



    }


    private void getInfo(HashMap<String,String> map ){


        OkGo.post(ARLConfig.productIntegralView)
                .tag("info")
                .params(map)
                .execute(new JsonCallback() {

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mOnProductListener.Error("加载失败");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        Log.e("产品详情", "onSuccess: "+s);

                        ResponseDetailClass responseClass = JSON.parseObject(
                                s, new TypeReference<ResponseDetailClass>() {
                                });

                        String message = responseClass.ErrorMessage;// 错误信息
                        String data = responseClass.rows;// 数据
                        String pictures = responseClass.detailPictures;// 图片
                        String propertys = responseClass.propertys;// 规格
                        String state = responseClass.totalCount;// 数据状态

                        if(Integer.parseInt(state)>0){

                            // 商品详情list
                            List<GoodsDetaill>  list = JSON.parseObject(data,
                                    new TypeReference<List<GoodsDetaill>>() {
                                    });
                            // 广告栏图片		detailPictures三个图片地址
                            List<PicturesClass> picStrings = JSON.parseObject(pictures,
                                    new TypeReference<List<PicturesClass>>() {
                                    });
                            // 规格list
                            List<Rule> rules = JSON.parseObject(propertys,
                                    new TypeReference<List<Rule>>() {
                                    });

                            mOnProductListener.Data(list,picStrings,rules);

                        }else{

                            mOnProductListener.Error("暂无数据");

                        }


                    }
                });

    }

    @Override
    public void getInfo(String nid) {

        HashMap<String,String> map = new HashMap<>();
        map.put("nid",nid);

        getInfo(map);

    }

    @Override
    public void add(String count,String type,String id) {

        HashMap<String,String> map = new HashMap<>();
        map.put("buyCount",count);
        map.put("productId",id);

        String newurl = ARLConfig.freeActivityApply;

        if(type.equals("gift")){

            newurl = ARLConfig.freeActivityApply;


        }else if (type.equals("integral")){

            newurl =ARLConfig.productIntegralApply;

        }

        add(map,type,newurl);

    }

    @Override
    public void onDestroy() {

        OkGo.getInstance().cancelTag("info");

    }
}
