package com.aorora.app.home.view;

import com.aorora.app.bean.GoodsDetaill;
import com.aorora.app.bean.PicturesClass;
import com.aorora.app.bean.Rule;

import java.util.List;

/**
 * Created by Administrator on 2017/5/31.
 */
public interface IProductView {

    void Error(String msg);

    void getData(List<GoodsDetaill> list, List<PicturesClass> path, List<Rule> rules);

    void addResult(String msg,String type);

    void addFailed(String msg);

}
