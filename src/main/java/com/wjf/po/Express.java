package com.wjf.po;


public class Express {
    private Integer expressId;
    private Integer goolId;
    private Integer expressFromId;
    private Integer expressToId;
    private String expressCode;
    private String expressFrom;
    private String expressTo;
    private String expressTime;
    private String expressType;
    private String expressPlace;
    private Integer courierId;

    private AdminInfo expressFromInfo;
    private AdminInfo expressToInfo;

    private Gool gool;

    private Integer pageSize;
    private Integer pageIndex;

    private Integer flag;
    public Express() {

    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public Integer getExpressId() {
        return expressId;
    }

    public void setExpressId(Integer expressId) {
        this.expressId = expressId;
    }

    public Integer getGoolId() {
        return goolId;
    }

    public void setGoolId(Integer goolId) {
        this.goolId = goolId;
    }

    public Integer getExpressFromId() {
        return expressFromId;
    }

    public void setExpressFromId(Integer expressFromId) {
        this.expressFromId = expressFromId;
    }

    public Integer getExpressToId() {
        return expressToId;
    }

    public void setExpressToId(Integer expressToId) {
        this.expressToId = expressToId;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getExpressFrom() {
        return expressFrom;
    }

    public void setExpressFrom(String expressFrom) {
        this.expressFrom = expressFrom;
    }

    public String getExpressTo() {
        return expressTo;
    }

    public void setExpressTo(String expressTo) {
        this.expressTo = expressTo;
    }

    public String getExpressTime() {
        return expressTime;
    }

    public void setExpressTime(String expressTime) {
        this.expressTime = expressTime;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    public String getExpressPlace() {
        return expressPlace;
    }

    public void setExpressPlace(String expressPlace) {
        this.expressPlace = expressPlace;
    }

    public AdminInfo getExpressFromInfo() {
        return expressFromInfo;
    }

    public void setExpressFromInfo(AdminInfo expressFromInfo) {
        this.expressFromInfo = expressFromInfo;
    }

    public AdminInfo getExpressToInfo() {
        return expressToInfo;
    }

    public void setExpressToInfo(AdminInfo expressToInfo) {
        this.expressToInfo = expressToInfo;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

    public Gool getGool() {
        return gool;
    }

    public void setGool(Gool gool) {
        this.gool = gool;
    }

    @Override
    public String toString() {
        return "AdminInfo [expressId=" + expressId + ", goolId=" + goolId + ", expressFromId="
                + expressFromId + ", expressToId=" + expressToId + ", expressCode=" + expressCode + ", expressFrom="
                + expressFrom + ", expressTo=" + expressTo + ", expressTime=" + expressTime + ", expressType=" + expressType + ", expressPlace=" + expressPlace +   "]";
    }
}