package com.wjf.mapper;

import com.wjf.po.Courier;

import java.util.List;

public interface CourierMapper {
    public List<Courier> getCourier(Courier courier);
    public int deleteCourier(Integer id);
    public int insertCourier(Courier courier);
    public void updateCourier(Courier courier);

}