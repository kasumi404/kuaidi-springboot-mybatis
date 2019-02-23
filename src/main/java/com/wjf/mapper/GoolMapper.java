package com.wjf.mapper;

import com.wjf.po.Gool;

import java.util.List;

public interface GoolMapper {
    public List<Gool> getGool(Gool gool);
    public int insertGool(Gool gool);
    public void updateGool(Gool gool);
    public int deleteGool(Integer id);


}