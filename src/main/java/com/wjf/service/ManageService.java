package com.wjf.service;

import com.wjf.mapper.ManageMapper;
import com.wjf.po.Manage;
import com.wjf.po.Express;
import com.wjf.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageService {
    SqlSession sqlSession = null;
    public Map login(Manage order){
        List<Manage> manages = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

            manages = manageMapper.getManage(order);
            if(manages.size() != 0){
                if (manages.get(0).getPassword().equals(order.getPassword())){
                    map.put("manage",manages.get(0));
                    map.put("message","成功");
                    map.put("result",200);
                }else {
                    map.put("message","密码错误");
                    map.put("result",400);
                }
            }else {
                map.put("message","不存在用户名");
                map.put("result",400);
            }
            sqlSession.commit();

        } catch (Exception e) {
            System.out.println("error:"+e);
            map.put("message",e.toString());
            map.put("result",500);
            sqlSession.rollback();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return map;
    }

    //注册
    public Map register(Manage order){
        List<Manage> manages = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);

            manages = manageMapper.getManage(order);

            if (manages.size() != 0){
                map.put("message","已有此用户名");
                map.put("result",400);
            }else {
                manageMapper.insertManage(order);
                if (order.getId() != null){
                    map.put("message","成功");
                    map.put("result",200);
                }else {
                    map.put("message","数据有误");
                    map.put("result",600);
                }
            }
            sqlSession.commit();

        } catch (Exception e) {
            System.out.println("error:"+e);
            map.put("message",e.toString());
            map.put("result",500);
            sqlSession.rollback();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return map;
    }

    //修改密码 用户名。/
    public Map updateManage(Manage order){
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
            manageMapper.updateManage(order);
            map.put("message","成功");
            map.put("result",200);
            sqlSession.commit();

        } catch (Exception e) {
            System.out.println("error:"+e);
            map.put("message",e.toString());
            map.put("result",500);
            sqlSession.rollback();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return map;
    }

    //获得所有/
    public Map getManage(Manage order){
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
            List<Manage> manages = manageMapper.getManage(order);
            map.put("manages",manages);
            map.put("message","成功");
            map.put("result",200);
            sqlSession.commit();

        } catch (Exception e) {
            System.out.println("error:"+e);
            map.put("message",e.toString());
            map.put("result",500);
            sqlSession.rollback();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return map;
    }
    //获得所有/
    public Map deleteManage(Manage order){
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
            int manages = manageMapper.deleteManage(order.getId());
            map.put("manages",manages);
            map.put("message","成功");
            map.put("result",200);
            sqlSession.commit();

        } catch (Exception e) {
            System.out.println("error:"+e);
            map.put("message",e.toString());
            map.put("result",500);
            sqlSession.rollback();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return map;
    }
}
