package com.aorora.app.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aorora.app.R;
import com.aorora.app.banner.Banner;
import com.aorora.app.banner.loader.GlideImageLoader;
import com.aorora.app.base.BaseFragment;
import com.aorora.app.bean.Advertisement;
import com.aorora.app.bean.IndustrialClass;
import com.aorora.app.cluster.MemberListActivity;
import com.aorora.app.cluster.prezent.i.IClusterPresenter;
import com.aorora.app.cluster.prezent.iml.ClusterPresenter;
import com.aorora.app.cluster.view.IClusterView;
import com.aorora.app.uitls.ARLConfig;
import com.aorora.app.uitls.AppPreference;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/18.
 * 产业集群
 */
public class ClusterFragment extends BaseFragment implements IClusterView {

    @Bind(R.id.banner)
    Banner mBanner;
    @Bind(R.id.customerGradeName)  //职位
    TextView mCustomerGradeName;
    @Bind(R.id.findmymember)       //产看我的会员
    TextView mFindmymember;
    @Bind(R.id.findmember)      //会员查询
    TextView mFindmember;
    @Bind(R.id.customerCommissionTotal_commend) //推广收入
    TextView mCustomerCommissionTotalCommend;
    @Bind(R.id.customerCommissionTotal_manager) //管理收入
    TextView mCustomerCommissionTotalManager;
    @Bind(R.id.promotionalrevenuebreakdown)   //推广收入明细
    TextView mPromotionalrevenuebreakdown;
    @Bind(R.id.manageincomedetails)         //管理收入明细
    TextView mManageincomedetails;
    @Bind(R.id.incomedistributiondetails) //收入发放明细
    TextView mIncomedistributiondetails;
    @Bind(R.id.customerCommissionTotal_product)//产品收入下的  收入总额
    TextView mCustomerCommissionTotalProduct;
    @Bind(R.id.viewincomedetails)               //产看收入明细
    TextView mViewincomedetails;
    @Bind(R.id.applicationforwithdrawal)        //申请提现
    TextView mApplicationforwithdrawal;
    @Bind(R.id.memberAreaOrderCountAll) //理事砸蛋下的  收入总额
    TextView mMemberAreaOrderCountAll;
    @Bind(R.id.memberAreaOrderCountNew) //品质商城占额
    TextView mMemberAreaOrderCountNew;
    @Bind(R.id.applicationforincomedistribution) //申请收入发放
    TextView mApplicationforincomedistribution;
    @Bind(R.id.incomedistributionrecord)    //收入发放记录
    TextView mIncomedistributionrecord;
    @Bind(R.id.viewincomedistributiondetails)  //查看收入明细
    TextView mViewincomedistributiondetails;
    @Bind(R.id.thenewdeclaration)           //新增报单
    TextView mThenewdeclaration;
    @Bind(R.id.checkthedeclaration)         //产看报单
    TextView mCheckthedeclaration;
    @Bind(R.id.managementelitemembers)      //管理我的商务精英群成员
    RelativeLayout mManagementelitemembers;
    @Bind(R.id.productsubsidybalance)       //品质商城产品补助积分余额
    RelativeLayout mProductsubsidybalance;
    @Bind(R.id.memberProductIntegralTotalA) //A类产品补助积分：0.0分
    TextView mMemberProductIntegralTotalA;
    @Bind(R.id.TotalA)          //进入兑换专区 A
    TextView mTotalA;
    @Bind(R.id.memberProductIntegralTotalB)//B类产品补助积分：0.0分
    TextView mMemberProductIntegralTotalB;
    @Bind(R.id.TotalB)          //进入兑换专区 B
    TextView mTotalB;
    @Bind(R.id.memberProductIntegralTotalC)//C类产品补助积分：0.0分
    TextView mMemberProductIntegralTotalC;
    @Bind(R.id.TotalC)          //进入兑换专区 C
    TextView mTotalC;
    @Bind(R.id.memberProductIntegralTotalD)//D类产品补助积分：0.0分
    TextView mMemberProductIntegralTotalD;
    @Bind(R.id.TotalD)          //进入兑换专区 D
    TextView mTotalD;
    @Bind(R.id.isLogin_lin)
    LinearLayout mIsLoginLin;
    @Bind(R.id.clu_refresh)
    TwinklingRefreshLayout mCluRefresh;

    private IClusterPresenter iclusterpresenter;

    public ClusterFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cluster_fragment, container, false);

        ButterKnife.bind(this, view);
        initview();
        initdata();

        return view;
    }

    /**
     * 设置控件属性
     */
    private void initview() {

        mCluRefresh.setFloatRefresh(true);
        mCluRefresh.setEnableLoadmore(false);
        mCluRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);

                initdata();

            }
        });

    }

    /**
    *
    * @author : Administrator
    * @time : 2017/6/2  10:05
    * @param :初始化数据
    */

    private void initdata() {

        showLoading();
        iclusterpresenter = new ClusterPresenter(this);
        iclusterpresenter.onDate(); //主页数据
        iclusterpresenter.onBanner(); //广告数据
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iclusterpresenter.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.findmymember, R.id.findmember, R.id.promotionalrevenuebreakdown, R.id.manageincomedetails, R.id.incomedistributiondetails, R.id.viewincomedetails, R.id.applicationforwithdrawal, R.id.applicationforincomedistribution, R.id.incomedistributionrecord, R.id.viewincomedistributiondetails, R.id.thenewdeclaration, R.id.checkthedeclaration, R.id.TotalA, R.id.TotalB, R.id.TotalC, R.id.TotalD})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.findmymember:  //我的会员

                Intent fmm = new Intent();
                fmm.putExtra("cosType", "0");
                fmm.setClass(getActivity(), MemberListActivity.class);
                startActivity(fmm);

                break;
            case R.id.findmember:   //会员查询


                break;
            case R.id.promotionalrevenuebreakdown: //推广收入明细


                break;
            case R.id.manageincomedetails:  //管理收入明细



                break;
            case R.id.incomedistributiondetails: //收入发放明细



                break;
            case R.id.viewincomedetails:   //产看收入明细



                break;
            case R.id.applicationforwithdrawal:   //申请提现



                break;
            case R.id.applicationforincomedistribution:  //申请收入发放
                break;
            case R.id.incomedistributionrecord: //收入发放记录
                break;
            case R.id.viewincomedistributiondetails: //查看收入明细
                break;
            case R.id.thenewdeclaration:  //新增报单
                break;
            case R.id.checkthedeclaration:   //产看报单
                break;
            case R.id.TotalA:
                break;
            case R.id.TotalB:
                break;
            case R.id.TotalC:
                break;
            case R.id.TotalD:
                break;
        }
    }

    /**
    *
    * @author : Administrator
    * @time : 2017/6/2  10:02
    * @param : 请求返回的数据
    */
    @Override
    public void handleData(List<IndustrialClass> data) {

        dismissLoading();
        mCluRefresh.finishRefreshing();
        if(null!=data){
            updataUI(data);
        }

    }

    private void updataUI(List<IndustrialClass> data) {

        IndustrialClass industrialClass  = data.get(0);

        //产业集群ID
        AppPreference.getAppPreference().putString("customerId",industrialClass.getCustomerId());

        mCustomerGradeName.setText(""+industrialClass.getCustomerGradeName());

        mCustomerCommissionTotalCommend.setText("￥"
                + industrialClass.getCustomerCommissionTotal_commend());

        mCustomerCommissionTotalManager.setText("￥"
                + industrialClass.getCustomerCommissionTotal_manager());

        mCustomerCommissionTotalProduct.setText("￥"
                + industrialClass.getCustomerCommissionTotal_product());

        mMemberAreaOrderCountAll.setText("￥"
                + industrialClass.getMemberAreaOrderCountAll());

        mMemberAreaOrderCountNew.setText("￥"
                + industrialClass.getMemberAreaOrderCountNew());
        mMemberProductIntegralTotalA.setText("A类产品补助积分："
                + industrialClass.getMemberProductIntegralTotalA() + "分");

        mMemberProductIntegralTotalB.setText("B类产品补助积分："
                + industrialClass.getMemberProductIntegralTotalB() + "分");
        mMemberProductIntegralTotalC.setText("C类产品补助积分："
                + industrialClass.getMemberProductIntegralTotalC() + "分");

        mMemberProductIntegralTotalD.setText("D类产品补助积分："
                + industrialClass.getMemberProductIntegralTotalD() + "分");

    }


    /**
     * 数据请求失败
     * @param msg
     */
    @Override
    public void onError(String msg) {

        dismissLoading();
        mCluRefresh.finishRefreshing();
        showShortToast("加载失败");
    }

    /**
     * 请求广告页数据
     * @param bannerlist
     */
    @Override
    public void getBanner(List<Advertisement> bannerlist) {

        dismissLoading();
        mCluRefresh.finishRefreshing();
        if(null!=bannerlist){

            List<String> str = new ArrayList<>();
            for (int i = 0; i <bannerlist.size() ; i++) {

                str.add(ARLConfig.URL+bannerlist.get(i).getPicture());
            }

            if (bannerlist.size()==1) {
                str.add(ARLConfig.URL+bannerlist.get(0).getPicture());
            }

            mBanner.setImages(str)
                   .setImageLoader(new GlideImageLoader())
                   .start();
        }
    }

    /**
     * 广告页数据请求失败
     * @param msg
     */
    @Override
    public void onBannerError(String msg) {

        dismissLoading();
        mCluRefresh.finishRefreshing();
        showShortToast("加载失败");
    }


}
