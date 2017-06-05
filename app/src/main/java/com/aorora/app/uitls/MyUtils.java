package com.aorora.app.uitls;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.design.widget.AppBarLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26.
 */
public class MyUtils {


    /**
     *
     *
     *debug      zzzzzzzzzzzzz
     *
     *
     * 获取屏幕的宽和高
     * @param context
     * @return
     */
    public static Map<String,Integer> GetMetrics(Context context){

        Map<String,Integer> map = new HashMap<>();

        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wn = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wn.getDefaultDisplay().getMetrics(metric);

        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）

        map.put("Hight",height);
        map.put("Width",width);

        return map;
    }


    /**
     * 设置AppBae的滑动监听和TwinklingRefreshLayout搭配使用
     * @param appBar
     * @param refresh
     */

    public static void setAppBar(AppBarLayout appBar, final TwinklingRefreshLayout refresh){

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    refresh.setEnableRefresh(true);
                    refresh.setEnableOverScroll(false);
                } else {
                    refresh.setEnableRefresh(false);
                    refresh.setEnableOverScroll(false);
                }
            }
        });

    }


    /**
     * 检测是否有网络
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
	         /* ConnectivityManager mConnectivityManager = (ConnectivityManager) context
	                  .getSystemService(Context.CONNECTIVITY_SERVICE);
	          NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
	          if (mNetworkInfo != null) {
	              return mNetworkInfo.isAvailable();
	          }  */
            if(MyUtils.isWifiAvailable(context)) {
                return true;
            } else if(MyUtils.is3GAvailable(context)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    /**
     * @Title: isWifiAvailable
     * @Description: 检测wifi网络是否可用
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean isWifiAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
    }

    /**
     * @Title: isWifiAvailable
     * @Description: 检测wifi网络是否可用
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean is3GAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
    }


    /**
     * 获取动态加载的扩展View（布局）
     * @Title: getLayoutFromInflater
     * @Description: (这里用一句话描述这个方法的作用)
     * @param @param context
     * @param @param layoutName
     * @param @return    设定文件
     * @return View    返回类型
     * @throws
     */
    public static View getLayoutFromInflater(Context context, int layoutName) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(layoutName, null);
        return layout;
    }

}
