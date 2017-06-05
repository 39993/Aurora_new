package com.aorora.app.home.prezent.listener;

import com.aorora.app.bean.GoodsDetaill;
import com.aorora.app.bean.PicturesClass;
import com.aorora.app.bean.Rule;

import java.util.List;

/**
 * Created by Administrator on 2017/5/31.
 */
public interface onProductListener {

    void Error(String msg);

    void Data(List<GoodsDetaill> list, List<PicturesClass> path, List<Rule> rules);

    void Cart(String count,String type);

    void addFailded(String msg);

}
