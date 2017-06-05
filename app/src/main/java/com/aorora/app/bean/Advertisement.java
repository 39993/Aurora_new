package com.aorora.app.bean;

/**
 * author : Administrator on 2017/6/2.
 * time : 2017/06/02
 * fileNmae : ${fileName}
 * desc :  广告数据实体类
 */
public class Advertisement {

    private String picture;
    private String title;
    private String url;
    private String id;


//    public Advertisement(String picture, String title, String url, String id) {
//        this.picture = picture;
//        this.title = title;
//        this.url = url;
//        this.id = id;
//    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "picture='" + picture + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
