package com.aorora.app.bean;

/**
 * author : Administrator on 2017/6/3.
 * time : 2017/06/03
 * fileNmae : ${fileName}
 * desc :
 */
public class MyMemberClass {


    //	  "number": "2148287",
//      "directTotalCount": "0",
//      "mobile": "",
//      "phone": "",
//      "weixinName": "",
//      "weixin": "15225440720",
//      "registerTime": "2017-02-08 18:38:38",
//      "url": "1486550318422",
//      "gradeName": "普通会员",
//      "parentNumber": "4272059",
//      "headUrl": "",
//      "id": "2261688"

    private Boolean isput = true;

    private String number;
    private String parentNumber;
    private String gradeName;
    private String headUrl;
    private String id;
    private String url;

    private String createTime;
    private String redPaperExpireTime;
    private String mobile;
    private String directTotalCount;
    private String phone;
    private String weixinName;
    private String weixin;
    private String registerTime;




    @Override
    public String toString() {
        return "MyMemberClass{" +
                "createTime='" + createTime + '\'' +
                ", redPaperExpireTime='" + redPaperExpireTime + '\'' +
                ", mobile='" + mobile + '\'' +
                ", number='" + number + '\'' +
                ", parentNumber='" + parentNumber + '\'' +
                ", gradeName='" + gradeName + '\'' +
                ", id='" + id + '\'' +
                ", directTotalCount='" + directTotalCount + '\'' +
                ", phone='" + phone + '\'' +
                ", weixinName='" + weixinName + '\'' +
                ", weixin='" + weixin + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", url='" + url + '\'' +
                ", headUrl='" + headUrl + '\'' +
                '}';
    }


    public Boolean getIsput() {
        return isput;
    }

    public void setIsput(Boolean isput) {
        this.isput = isput;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRedPaperExpireTime() {
        return redPaperExpireTime;
    }

    public void setRedPaperExpireTime(String redPaperExpireTime) {
        this.redPaperExpireTime = redPaperExpireTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDirectTotalCount() {
        return directTotalCount;
    }

    public void setDirectTotalCount(String directTotalCount) {
        this.directTotalCount = directTotalCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeixinName() {
        return weixinName;
    }

    public void setWeixinName(String weixinName) {
        this.weixinName = weixinName;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
