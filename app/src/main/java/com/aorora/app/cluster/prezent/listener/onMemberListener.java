package com.aorora.app.cluster.prezent.listener;

import com.aorora.app.bean.MyMemberClass;

import java.util.List;

/**
 * author : Administrator on 2017/6/3.
 * time : 2017/06/03
 * fileNmae : ${fileName}
 * desc :
 */
public interface onMemberListener  {

    void getData(List<MyMemberClass> list);

    void getFailed(String msg);

    void getMore(List<MyMemberClass> list);

    void gerMoreFailed(String msg);

}
