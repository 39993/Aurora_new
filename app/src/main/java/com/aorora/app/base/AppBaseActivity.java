package com.aorora.app.base;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aorora.app.R;


public class AppBaseActivity extends BaseActivity {

    private LinearLayout mBaseLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_action_bar);

        initViews();
    }


    /**
     * 设置回退
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
         if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void setContentView(int layoutResID) {
        setContentView(getLayoutInflater().inflate(layoutResID, mBaseLayout, false));
    }

    private void initViews() {
        mBaseLayout = (LinearLayout) findViewById(R.id.abc_base_layout);
    }

    @Override
    public void setContentView(View view) {
        mBaseLayout.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mBaseLayout.addView(view, params);
    }

}
