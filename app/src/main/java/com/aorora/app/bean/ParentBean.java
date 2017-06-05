package com.aorora.app.bean;

/**
 * author : Administrator on 2017/6/3.
 * time : 2017/06/03
 * fileNmae : ${fileName}
 * desc :
 */
public class ParentBean {


    private String number;  //会员编号
    private String gradeName;  //身份
    private String parentNumber;   //推广人编号吗
    private String headUrl;     //头像
    private String id;
    private String url;

    private ChildBean childBean;

    public ChildBean getChildBean() {
        return childBean;
    }

    public void setChildBean(ChildBean childBean) {
        this.childBean = childBean;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
