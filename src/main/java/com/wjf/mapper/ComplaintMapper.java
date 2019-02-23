package com.wjf.mapper;

import com.wjf.po.Complaint;

import java.util.List;

public interface ComplaintMapper {
    public List<Complaint> getComplaint(Complaint complaint);
    public int insertComplaint(Complaint complaint);
    public int deleteComplaint(Integer id);
    public void updateComplaint(Complaint complaint);


}