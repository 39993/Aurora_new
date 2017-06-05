package com.aorora.app.cluster;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.aorora.app.R;
import com.aorora.app.adapter.MemberAdapter;
import com.aorora.app.base.AppBaseActivity;
import com.aorora.app.bean.MyMemberClass;
import com.aorora.app.cluster.prezent.i.IMemberPresenter;
import com.aorora.app.cluster.prezent.iml.MemberPresenter;
import com.aorora.app.cluster.view.IMemberView;
import com.aorora.app.uitls.AppPreference;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author : Administrator on 2017/6/1.
 * time : 2017/06/01
 * fileNmae : MemberListActivity.java
 * desc : 产业集群我的会员 列表
 */
public class MemberListActivity extends AppBaseActivity implements IMemberView {


    @Bind(R.id.member_list)
    ListView mMemberList;
    private IMemberPresenter mPresenter;

    private int startSize = 0;// 分页起始码
    private int getCount = 10;// 分页数据项
    private String keyStr = "";
    private String cosType = "3";
    private String customerId;
    private MemberAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        ButterKnife.bind(this);

        cosType = getIntent().getStringExtra("cosType");  //0查看   1检索
        customerId = AppPreference.getAppPreference().getString("customerId", "1");


        initdata();

        adapter = new MemberAdapter(MemberListActivity.this);
        mMemberList.setAdapter(adapter);

    }


    private void initdata() {

        mPresenter = new MemberPresenter(this);
        mPresenter.onGetData(customerId, startSize + "", getCount + "", keyStr, cosType);

    }


    @Override
    public void handleData(List<MyMemberClass> list) {

        if (null == list) return;

        Log.e("会员", "handleData: "+list.toString() );

        adapter.setList(list);


    }

    @Override
    public void onError(String msg) {


    }

    @Override
    public void loadMore(List<MyMemberClass> list) {

    }

    @Override
    public void loadMoreResult(String msg) {


    }


}
