package com.aorora.app.bean;

/**
 * author : Administrator on 2017/6/2.
 * time : 2017/06/02
 * fileNmae : ${fileName}
 * desc :  产业集群主页数据实体类
 */
public class IndustrialClass {

    private String customerId;// 产业集群的ID
    private String customerGradeName;// 产业集群等级名称
    private String customerChildTotalCount;// 我的会员总数
    private String customerCommissionTotal_commend;// 推广收入余额
    private String customerCommissionTotal_manager;// 管理收入余额
    private String customerCommissionTotal_product;// 产品收入余额
    private String memberAreaOrderCountAll;// 砸蛋订单可用收入
    private String memberAreaOrderCountNew;// 其中品质商城已收入
    private String memberProductIntegralTotalA;// A类补助积分余额
    private String memberProductIntegralTotalB;// B类补助积分余额
    private String memberProductIntegralTotalC;// C类补助积分余额
    private String memberProductIntegralTotalD;// D类补助积分余额


//    public IndustrialClass(String customerId, String customerGradeName, String customerChildTotalCount, String customerCommissionTotal_commend, String customerCommissionTotal_manager, String customerCommissionTotal_product, String memberAreaOrderCountAll, String memberAreaOrderCountNew, String memberProductIntegralTotalA, String memberProductIntegralTotalB, String memberProductIntegralTotalC, String memberProductIntegralTotalD) {
//        this.customerId = customerId;
//        this.customerGradeName = customerGradeName;
//        this.customerChildTotalCount = customerChildTotalCount;
//        this.customerCommissionTotal_commend = customerCommissionTotal_commend;
//        this.customerCommissionTotal_manager = customerCommissionTotal_manager;
//        this.customerCommissionTotal_product = customerCommissionTotal_product;
//        this.memberAreaOrderCountAll = memberAreaOrderCountAll;
//        this.memberAreaOrderCountNew = memberAreaOrderCountNew;
//        this.memberProductIntegralTotalA = memberProductIntegralTotalA;
//        this.memberProductIntegralTotalB = memberProductIntegralTotalB;
//        this.memberProductIntegralTotalC = memberProductIntegralTotalC;
//        this.memberProductIntegralTotalD = memberProductIntegralTotalD;
//    }


    @Override
    public String toString() {
        return "IndustrialClass{" +
                "customerId='" + customerId + '\'' +
                ", customerGradeName='" + customerGradeName + '\'' +
                ", customerChildTotalCount='" + customerChildTotalCount + '\'' +
                ", customerCommissionTotal_commend='" + customerCommissionTotal_commend + '\'' +
                ", customerCommissionTotal_manager='" + customerCommissionTotal_manager + '\'' +
                ", customerCommissionTotal_product='" + customerCommissionTotal_product + '\'' +
                ", memberAreaOrderCountAll='" + memberAreaOrderCountAll + '\'' +
                ", memberAreaOrderCountNew='" + memberAreaOrderCountNew + '\'' +
                ", memberProductIntegralTotalA='" + memberProductIntegralTotalA + '\'' +
                ", memberProductIntegralTotalB='" + memberProductIntegralTotalB + '\'' +
                ", memberProductIntegralTotalC='" + memberProductIntegralTotalC + '\'' +
                ", memberProductIntegralTotalD='" + memberProductIntegralTotalD + '\'' +
                '}';
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerGradeName() {
        return customerGradeName;
    }

    public void setCustomerGradeName(String customerGradeName) {
        this.customerGradeName = customerGradeName;
    }

    public String getCustomerChildTotalCount() {
        return customerChildTotalCount;
    }

    public void setCustomerChildTotalCount(String customerChildTotalCount) {
        this.customerChildTotalCount = customerChildTotalCount;
    }

    public String getCustomerCommissionTotal_commend() {
        return customerCommissionTotal_commend;
    }

    public void setCustomerCommissionTotal_commend(String customerCommissionTotal_commend) {
        this.customerCommissionTotal_commend = customerCommissionTotal_commend;
    }

    public String getCustomerCommissionTotal_manager() {
        return customerCommissionTotal_manager;
    }

    public void setCustomerCommissionTotal_manager(String customerCommissionTotal_manager) {
        this.customerCommissionTotal_manager = customerCommissionTotal_manager;
    }

    public String getCustomerCommissionTotal_product() {
        return customerCommissionTotal_product;
    }

    public void setCustomerCommissionTotal_product(String customerCommissionTotal_product) {
        this.customerCommissionTotal_product = customerCommissionTotal_product;
    }

    public String getMemberAreaOrderCountAll() {
        return memberAreaOrderCountAll;
    }

    public void setMemberAreaOrderCountAll(String memberAreaOrderCountAll) {
        this.memberAreaOrderCountAll = memberAreaOrderCountAll;
    }

    public String getMemberAreaOrderCountNew() {
        return memberAreaOrderCountNew;
    }

    public void setMemberAreaOrderCountNew(String memberAreaOrderCountNew) {
        this.memberAreaOrderCountNew = memberAreaOrderCountNew;
    }

    public String getMemberProductIntegralTotalA() {
        return memberProductIntegralTotalA;
    }

    public void setMemberProductIntegralTotalA(String memberProductIntegralTotalA) {
        this.memberProductIntegralTotalA = memberProductIntegralTotalA;
    }

    public String getMemberProductIntegralTotalB() {
        return memberProductIntegralTotalB;
    }

    public void setMemberProductIntegralTotalB(String memberProductIntegralTotalB) {
        this.memberProductIntegralTotalB = memberProductIntegralTotalB;
    }

    public String getMemberProductIntegralTotalC() {
        return memberProductIntegralTotalC;
    }

    public void setMemberProductIntegralTotalC(String memberProductIntegralTotalC) {
        this.memberProductIntegralTotalC = memberProductIntegralTotalC;
    }

    public String getMemberProductIntegralTotalD() {
        return memberProductIntegralTotalD;
    }

    public void setMemberProductIntegralTotalD(String memberProductIntegralTotalD) {
        this.memberProductIntegralTotalD = memberProductIntegralTotalD;
    }
}
