package com.aorora.app.bean;

/**
 * Created by Administrator on 2017/5/27.
 */
public class InteBean {

    //	"name": "化妆品/个人护理",
//    "id": "1068",
//    "simpleName": "护肤品区",
//    "thumbnail": "null"

//	"name": "路路通转运珠项链",
//  "price": "46.0",
//  "marketPrice": "188.0",
//  "thumbnail": "/upload/20160918082144.jpg",
//  "url": "1468029808925",
//  "id": "4009"


    public String simpleName;
    public String name;//产品名称
    public String needIntegral;//需要多少积分兑换
    public String url;//产品的URL
    public String thumbnail;//产品的缩略图
    public String artId;//信息ID
    public String productId;//产品ID
//	public String productType;//这个是产品分类的JSON数据

    public String price;
    public String marketPrice;
    public String id;
    public String audit;  //库存 为0 已经售罄
    public String stock;


    @Override
    public String toString() {
        return "GoodsClass [name=" + name + ", needIntegral=" + needIntegral + ", url=" + url + ", thumbnail="
                + thumbnail + ", artId=" + artId + ", productId=" + productId + ", price=" + price + ", marketPrice="
                + marketPrice + ", id=" + id + ", audit=" + audit + ", stock=" + stock + "]";
    }



}
