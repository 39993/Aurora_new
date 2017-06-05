package com.aorora.app.membercenter;

import android.os.Bundle;

import com.aorora.app.R;
import com.aorora.app.base.AppBaseActivity;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

/**
 *
 * @author Administrator
 * @文件名 AccountActivity.java
 * @注释 我的账户资产
 */

public class AccountActivity extends AppBaseActivity {

    private TwinklingRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        setDisplayHomeAsUpEnabled(true);
        setTitle("我的账户资产");

        iniview();
        initdata();
    }

    private void iniview() {

        refresh = (TwinklingRefreshLayout) findViewById(R.id.refresh);
        refresh.setEnableLoadmore(false);

    }

    private void initdata() {




    }


}
