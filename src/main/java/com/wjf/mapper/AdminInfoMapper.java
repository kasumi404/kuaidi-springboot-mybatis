package com.wjf.mapper;

import com.wjf.po.AdminInfo;

import java.util.List;

public interface AdminInfoMapper {
    public List<AdminInfo> getAdminInfo(AdminInfo adminInfo);
    public int insertAdminInfo(AdminInfo adminInfo);
    public void updateAdminInfo(AdminInfo adminInfo);
    public int deleteAdminInfo(Integer id);


}