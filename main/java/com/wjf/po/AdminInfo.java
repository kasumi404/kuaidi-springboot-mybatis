package com.wjf.po;

public class AdminInfo {
    private Integer adminId;
    private String userName;
    private String password;
    private String adminName;
    private String adminCell;
    private String adminIDCard;
    private String adminPlace;
    private String adminRoot;


    private Integer adminSended;
    private Integer adminSendno;
    private Integer adminSendok;
    private Integer adminGeted;
    private Integer adminGetno;
    private Integer adminGetok;

    private Integer pageSize;
    private Integer pageIndex;
    public AdminInfo() {

    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminCell() {
        return adminCell;
    }

    public void setAdminCell(String adminCell) {
        this.adminCell = adminCell;
    }

    public String getAdminIDCard() {
        return adminIDCard;
    }

    public void setAdminIDCard(String adminIDCard) {
        this.adminIDCard = adminIDCard;
    }

    public String getAdminPlace() {
        return adminPlace;
    }

    public void setAdminPlace(String adminPlace) {
        this.adminPlace = adminPlace;
    }

    public String getAdminRoot() {
        return adminRoot;
    }

    public void setAdminRoot(String adminRoot) {
        this.adminRoot = adminRoot;
    }

    public Integer getAdminSended() {
        return adminSended;
    }

    public void setAdminSended(Integer adminSended) {
        this.adminSended = adminSended;
    }

    public Integer getAdminSendno() {
        return adminSendno;
    }

    public void setAdminSendno(Integer adminSendno) {
        this.adminSendno = adminSendno;
    }

    public Integer getAdminSendok() {
        return adminSendok;
    }

    public void setAdminSendok(Integer adminSendok) {
        this.adminSendok = adminSendok;
    }

    public Integer getAdminGeted() {
        return adminGeted;
    }

    public void setAdminGeted(Integer adminGeted) {
        this.adminGeted = adminGeted;
    }

    public Integer getAdminGetno() {
        return adminGetno;
    }

    public void setAdminGetno(Integer adminGetno) {
        this.adminGetno = adminGetno;
    }

    public Integer getAdminGetok() {
        return adminGetok;
    }

    public void setAdminGetok(Integer adminGetok) {
        this.adminGetok = adminGetok;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminInfo [adminId=" + adminId + ", adminName=" + adminName + ", adminCell="
                + adminCell + ", adminIDCard=" + adminIDCard + ", adminIDCard=" + adminIDCard + ", adminPlace=" + adminPlace + ", adminRoot=" + adminRoot + "]";
    }
}