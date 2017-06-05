package com.aorora.app.cluster.prezent.iml;

import com.aorora.app.bean.MyMemberClass;
import com.aorora.app.cluster.model.MemberModle;
import com.aorora.app.cluster.model.i.IMemberModel;
import com.aorora.app.cluster.prezent.i.IMemberPresenter;
import com.aorora.app.cluster.prezent.listener.onMemberListener;
import com.aorora.app.cluster.view.IMemberView;

import java.util.List;

/**
 * author : Administrator on 2017/6/3.
 * time : 2017/06/03
 * fileNmae : ${fileName}
 * desc :
 */
public class MemberPresenter implements IMemberPresenter,onMemberListener {

    private IMemberView view;
    private IMemberModel mModel;

    public MemberPresenter(IMemberView view) {
        this.view = view;
        mModel = new MemberModle(this);
    }

    @Override
    public void onGetData(String customerId, String startsize, String getCount, String keyword, String cosType) {

        mModel.requster(customerId,startsize,getCount,keyword,cosType);
    }

    @Override
    public void onMore(String customerId, String startsize, String getCount, String keyword, String cosType) {

        mModel.More(customerId,startsize,getCount,keyword,cosType);

    }

    @Override
    public void onDestroy() {

        mModel.onDestroy();
    }





    @Override
    public void getData(List<MyMemberClass> list) {

        view.handleData(list);

    }

    @Override
    public void getFailed(String msg) {

        view.onError(msg);
    }

    @Override
    public void getMore(List<MyMemberClass> list) {

        view.loadMore(list);
    }

    @Override
    public void gerMoreFailed(String msg) {

        view.loadMoreResult(msg);
    }
}
