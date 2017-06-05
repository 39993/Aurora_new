package com.aorora.app.home.prezent.iml;

import com.aorora.app.bean.GoodsDetaill;
import com.aorora.app.bean.PicturesClass;
import com.aorora.app.bean.Rule;
import com.aorora.app.home.model.ProductModle;
import com.aorora.app.home.model.i.IProductModel;
import com.aorora.app.home.prezent.i.IProductPresenter;
import com.aorora.app.home.prezent.listener.onProductListener;
import com.aorora.app.home.view.IProductView;

import java.util.List;

/**
 * Created by Administrator on 2017/5/31.
 */
public class ProductPresenter implements IProductPresenter,onProductListener {


    private IProductView view;
    private IProductModel mModle;
    public ProductPresenter(IProductView view) {
        this.view = view;
        mModle = new ProductModle(this);
    }

    //Activity调用
    @Override
    public void getData(String nid) {

        mModle.getInfo(nid);

    }


    //Activity调用
    @Override
    public void addCart(String count,String type,String id) {

        mModle.add(count,type,id);

    }

    @Override
    public void onDestroy() {

        mModle.onDestroy();

    }


    @Override
    public void Error(String msg) {

        view.Error(msg);

    }

    //返回的数据
    @Override
    public void Data(List<GoodsDetaill> list, List<PicturesClass> path, List<Rule> rules) {

        view.getData(list,path,rules);

    }

    //加入购物车
    @Override
    public void Cart(String count,String type) {

        view.addResult(count,type);

    }

    @Override
    public void addFailded(String msg) {

        view.addFailed(msg);

    }


}
