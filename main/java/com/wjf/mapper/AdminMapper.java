package com.wjf.mapper;

import com.wjf.po.Admin;

public interface AdminMapper {
    public Admin getAdmin(Admin admin);
    public int deleteAdmin(Integer id);
    public int insertAdmin(Admin admin);
    public void updateAdmin(Admin admin);

}