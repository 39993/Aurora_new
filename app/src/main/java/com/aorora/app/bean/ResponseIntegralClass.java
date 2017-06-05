package com.aorora.app.bean;

/**
 * Created by Administrator on 2017/5/27.
 */
public class ResponseIntegralClass {

    private String ErrorMessage;

    private String rows;

    private String totalCount;

    private String integralBalance;

    private String productType;


    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getIntegralBalance() {
        return integralBalance;
    }

    public void setIntegralBalance(String integralBalance) {
        this.integralBalance = integralBalance;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


    @Override
    public String toString() {
        return "ResponseIntegralClass{" +
                "ErrorMessage='" + ErrorMessage + '\'' +
                ", rows='" + rows + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", integralBalance='" + integralBalance + '\'' +
                ", productType='" + productType + '\'' +
                '}';
    }



}
