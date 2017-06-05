package com.aorora.app.widget;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aorora.app.R;
import com.aorora.app.bean.CustomerService;
import com.aorora.app.bean.ResponseClass;
import com.aorora.app.okgo.JsonCallback;
import com.aorora.app.uitls.ARLConfig;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * author : Administrator on 2017/6/2.
 * time : 2017/06/02
 * fileNmae : ${fileName}
 * desc :  客服视图
 */
public class CustomPop extends PopupWindow implements View.OnClickListener {


    private Activity mContext;
    private ImageView code_image,delete_image;
    private Button phone_btn;
    private TextView content,workTime;

    public CustomPop(Activity context) {
        super(context);

        this.mContext = context;

        initview();
        initdata();
    }

    private boolean isInitCache = false;
    //获取二维码图片
    private void initdata() {

        OkGo.post(ARLConfig.customerService)
                .tag("pop")
                .cacheKey("popca")
                .cacheTime(5000)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new JsonCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                        Toast.makeText(mContext,"加载失败",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCacheSuccess(String s, Call call) {
                        super.onCacheSuccess(s, call);

                        if (!isInitCache) {
                            //缓存回调成功和网络回调成功做的事情是一样的,这里直接回调onSuccess
                            onSuccess(s, call, null);
                            isInitCache = true;
                        }
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

                            List<CustomerService> customerService = JSON.parseObject(data,
                                    new TypeReference<List<CustomerService>>() {
                                    });
                            if (customerService.get(0).content == null
                                    || customerService.get(0).content.equals("")) {
                                content.setText("扫描下方的二维码关注我们的微信公众号或这届拨打客服电话进行咨询！");
                            } else {
                                content.setText(customerService.get(0).content + "");
                            }

                            workTime.setText(Html.fromHtml("客服在线时间：<br> <font color=red>"
                                    + customerService.get(0).workTime + "</font>"));

                            Glide.with(mContext)
                                    .load(customerService.get(0).wechatQrcodePath)
                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                    .fitCenter()
                                    .into(code_image);

                        }else{



                        }

                    }
                });

    }

    private void initview() {

        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_pop,null);

        code_image = (ImageView) view.findViewById(R.id.code_image);
        phone_btn = (Button) view.findViewById(R.id.phone_btn);
        delete_image = (ImageView) view.findViewById(R.id.delete_image);
        content = (TextView) view.findViewById(R.id.content);
        workTime = (TextView) view.findViewById(R.id.workTime);

        delete_image.setOnClickListener(this);
        phone_btn.setOnClickListener(this);


        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wn = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wn.getDefaultDisplay().getMetrics(metric);

        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);

        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(width*3/4);
//        this.setHeight(height*3/4);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();

        WindowManager.LayoutParams lp =mContext.getWindow().getAttributes();
        lp.alpha = 0.5f;
        mContext.getWindow().setAttributes(lp);

        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp =mContext.getWindow().getAttributes();
                lp.alpha = 1.0f;
                mContext.getWindow().setAttributes(lp);

                OkGo.getInstance().cancelTag("pop");
            }
        });

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.delete_image:

                if (this.isShowing()) {
                    this.dismiss();
                }

                break;

            case R.id.phone_btn:   //拨打电话


                break;

        }

    }
}
