package com.aorora.app.bean;

/**
 * author : Administrator on 2017/6/1.
 * time : 2017/06/01
 * fileNmae : ResponseListClass.java
 * desc :
 */
public class ResponseListClass {

    //	{"error":"请输入有效金额","data":[],"count":"0"}
    private String error;

    private String data;

    private String count;


    public ResponseListClass(String error, String data, String count) {
        this.error = error;
        this.data = data;
        this.count = count;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "ResponseListClass{" +
                "error='" + error + '\'' +
                ", data='" + data + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
