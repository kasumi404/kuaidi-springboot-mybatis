package com.wjf.mapper;

import com.wjf.po.Express;

import java.util.List;

public interface ExpressMapper {
    public List<Express> getExpressByAdminId(Express express);
    public Express getExpressByExpressCode(Express express);
    public void insertExpress(Express express);
    public void updateExpress(Express express);
}