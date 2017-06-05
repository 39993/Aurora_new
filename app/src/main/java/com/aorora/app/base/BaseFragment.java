package com.aorora.app.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.widget.Toast;

/**
 * author : Administrator on 2017/6/2.
 * time : 2017/06/02
 * fileNmae : ${fileName}
 * desc :
 */
public class BaseFragment extends Fragment {


    protected AppBaseActivity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (AppBaseActivity) activity;
    }

    private ProgressDialog dialog;

    public void showLoading() {
        if (dialog != null && dialog.isShowing()) return;
        dialog = new ProgressDialog(mActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("正在加载中...");
        dialog.show();
    }

    public void dismissLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void showLongToast(String message) {
        showToast(message, Toast.LENGTH_LONG);
    }

    public void showShortToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    private Toast mToast;
    private void showToast(String message, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(mActivity, message, duration);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

}
