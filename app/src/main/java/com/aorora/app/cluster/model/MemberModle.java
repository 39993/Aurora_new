package com.aorora.app.cluster.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aorora.app.bean.MyMemberClass;
import com.aorora.app.bean.ResponseClass;
import com.aorora.app.cluster.model.i.IMemberModel;
import com.aorora.app.cluster.prezent.listener.onMemberListener;
import com.aorora.app.okgo.JsonCallback;
import com.aorora.app.uitls.ARLConfig;
import com.lzy.okgo.OkGo;

import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * author : Administrator on 2017/6/3.
 * time : 2017/06/03
 * fileNmae : ${fileName}
 * desc :
 */
public class MemberModle implements IMemberModel {


    private onMemberListener mListener;

    public MemberModle(onMemberListener listener) {
        mListener = listener;
    }

    private void load(String customerId, String startsize, String getCount, String keyword, String cosType){


        HashMap<String,String> map = new HashMap<>();

        map.put("customerId", customerId);// 产业集群ID
        map.put("startSize", startsize);// 分页起始码
        map.put("getCount", getCount);// 分页数据项
        map.put("keyword", keyword); //搜索内容

        String url= "";

        if(cosType.equals("0")){

            url = ARLConfig.customerChilds;

        }else if(cosType.equals("1")){

            url = ARLConfig.customerSearch;

        }

        OkGo.post(url)
                .params(map)
                .tag("mem")
                .execute(new JsonCallback() {

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                        mListener.getFailed("加载失败");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        Log.e("我的会员列表", "onSuccess: " + s);

                        ResponseClass responseClass = JSON.parseObject(
                                s, new TypeReference<ResponseClass>() {
                                });

                        String message = responseClass.ErrorMessage;// 错误信息
                        String data = responseClass.rows;// 数据
                        String state = responseClass.totalCount;// 数据状态

                        if (state != null && Integer.parseInt(state) > 0) {

                            List<MyMemberClass> Originallylist = JSON.parseObject(data,
                                    new TypeReference<List<MyMemberClass>>() {
                                    });

                            mListener.getData(Originallylist);


                        }else{

                            mListener.getFailed("加载失败");

                        }


                        }
                });


    }


    @Override
    public void requster(String customerId, String startsize, String getCount, String keyword, String cosType) {

        load( customerId, startsize, getCount, keyword, cosType);

    }

    @Override
    public void More(String customerId, String startsize, String getCount, String keyword, String cosType) {


        load( customerId, startsize, getCount, keyword, cosType);

    }

    @Override
    public void onDestroy() {

        OkGo.getInstance().cancelTag("mem");

    }
}
