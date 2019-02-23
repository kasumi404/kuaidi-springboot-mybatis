package com.wjf.service;

import com.wjf.mapper.AdminInfoMapper;
import com.wjf.mapper.ComplaintMapper;
import com.wjf.po.AdminInfo;
import com.wjf.po.Express;
import com.wjf.po.Complaint;
import com.wjf.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ComplaintService {
    SqlSession sqlSession = null;
    public Map getComplaint(Complaint order){
        Complaint complaint = new Complaint();
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            ComplaintMapper complaintMapper = sqlSession.getMapper(ComplaintMapper.class);
            List<Complaint> complaints = complaintMapper.getComplaint(order);
            for (Complaint complaintone:complaints) {
                AdminInfo adminInfo = new AdminInfo();
                adminInfo.setAdminId(complaintone.getAdminId());
                AdminInfoMapper adminInfoMapper = sqlSession.getMapper(AdminInfoMapper.class);
                complaintone.setAdminInfo(adminInfoMapper.getAdminInfo(adminInfo).get(0));
            }
            map.put("express",complaints);
            map.put("result",200);
            map.put("message","成功");
            sqlSession.commit();

        } catch (Exception e) {
            System.out.println("error:"+e);
            map.put("result",500);
            map.put("message",e.toString());
            sqlSession.rollback();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return map;
    }

    public Map updateComplaint(Complaint order){
        Complaint complaint = new Complaint();
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            ComplaintMapper complaintMapper = sqlSession.getMapper(ComplaintMapper.class);
            complaintMapper.updateComplaint(order);

            map.put("result",200);
            map.put("message","成功");
            sqlSession.commit();

        } catch (Exception e) {
            System.out.println("error:"+e);
            map.put("result",500);
            map.put("message",e.toString());
            sqlSession.rollback();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return map;
    }
    public Map insertComplaint(Complaint order){
        Complaint complaint = new Complaint();
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            ComplaintMapper complaintMapper = sqlSession.getMapper(ComplaintMapper.class);
            complaintMapper.insertComplaint(order);

            map.put("result",200);
            map.put("message","成功");
            sqlSession.commit();

        } catch (Exception e) {
            System.out.println("error:"+e);
            map.put("result",500);
            map.put("message",e.toString());
            sqlSession.rollback();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return map;
    }
    public Map deleteComplaint(Complaint order){
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            ComplaintMapper manageMapper = sqlSession.getMapper(ComplaintMapper.class);
            int manages = manageMapper.deleteComplaint(order.getComplaintId());
            map.put("manages",manages);
            map.put("message","成功");
            map.put("result",200);
            sqlSession.commit();

        } catch (Exception e) {

            System.out.println("error:"+e);
            map.put("message","请确认快递员的快递工作！");
            map.put("result",500);
            sqlSession.rollback();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
            return map;
        }
    }
}
