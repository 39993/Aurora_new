package com.aorora.app.cluster.view;

import com.aorora.app.bean.MyMemberClass;

import java.util.List;

/**
 * author : Administrator on 2017/6/3.
 * time : 2017/06/03
 * fileNmae : ${fileName}
 * desc :
 */
public interface IMemberView {

    void handleData(List<MyMemberClass> list);

    void onError(String msg);

    void loadMore(List<MyMemberClass> list);

    void loadMoreResult(String msg);

}
