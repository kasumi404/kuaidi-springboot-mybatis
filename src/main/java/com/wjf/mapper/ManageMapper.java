package com.wjf.mapper;

import com.wjf.po.Manage;
import java.util.List;

public interface ManageMapper {
    public List<Manage> getManage(Manage manage);
    public int deleteManage(Integer id);
    public int insertManage(Manage manage);
    public void updateManage(Manage manage);

}