package com.wjf.po;

public class Courier {
    private Integer courierId;
    private String courierName;
    private String courierCall;
    private String courierIdCard;

    private Integer pageSize;
    private Integer pageIndex;
    public Courier() {

    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public String getCourierCall() {
        return courierCall;
    }

    public void setCourierCall(String courierCall) {
        this.courierCall = courierCall;
    }

    public String getCourierIdCard() {
        return courierIdCard;
    }

    public void setCourierIdCard(String courierIdCard) {
        this.courierIdCard = courierIdCard;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}