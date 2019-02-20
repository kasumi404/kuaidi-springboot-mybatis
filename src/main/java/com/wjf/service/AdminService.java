package com.wjf.service;

import com.wjf.mapper.AdminInfoMapper;
import com.wjf.mapper.AdminMapper;
import com.wjf.po.Admin;
import com.wjf.po.AdminInfo;
import com.wjf.po.Express;
import com.wjf.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminService {
    SqlSession sqlSession = null;
    public Map login(Admin order){
        Admin admin = new Admin();
        AdminInfo adminInfo = new AdminInfo();
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

            admin = adminMapper.getAdmin(order);

            if (admin != null&& admin.getPassword().equals(order.getPassword())){
                AdminInfoMapper adminInfoMapper = sqlSession.getMapper(AdminInfoMapper.class);
                adminInfo.setAdminId(admin.getId());
                adminInfo = adminInfoMapper.getAdminInfo(adminInfo).get(0);
                map.put("message","成功");
                map.put("result",200);
            }else {
                map.put("message","用户名或密码错误");
                map.put("result",400);
            }
            map.put("adminInfo",adminInfo);
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

        if (adminInfo.getAdminId() != null){
            Express express = new Express();
            ExpressService es = new ExpressService();
            express.setExpressId(adminInfo.getAdminId());
            express.setExpressType("全部");
            express.setFlag(1);
            map.put("expressSend",es.getExpressByAdminId(express));
            express.setFlag(2);
            map.put("expressTo",es.getExpressByAdminId(express));
        }
        return map;
    }

    //注册
    public Map register(Admin order,AdminInfo order2){
        Admin admin = new Admin();
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

            admin = adminMapper.getAdmin(order);

            if (admin != null){
                map.put("message","已有此用户名");
                map.put("result",400);
            }else {
                adminMapper.insertAdmin(order);
                if (order.getId() != null){
                    AdminInfoMapper adminInfoMapper = sqlSession.getMapper(AdminInfoMapper.class);
                    order2.setAdminId(order.getId());
                    adminInfoMapper.insertAdminInfo(order2);
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

    //编辑。/
    public Map edit(Admin order,AdminInfo order2){
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            AdminInfoMapper adminInfoMapper = sqlSession.getMapper(AdminInfoMapper.class);
            adminInfoMapper.updateAdminInfo(order2);
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
    //修改密码 用户名。/
    public Map updateAdmin(Admin order){
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
            adminMapper.updateAdmin(order);
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


    //获得用户信息
    public Map getAdminInfo(AdminInfo order){
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            AdminInfoMapper adminInfoMapper = sqlSession.getMapper(AdminInfoMapper.class);
            List<AdminInfo> adminInfos = adminInfoMapper.getAdminInfo(order);
            System.out.println(order);
            System.out.println(adminInfos);
            for(AdminInfo adminInfo : adminInfos){
                if(adminInfo.getAdminRoot().equals("1")){
                    AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
                    Admin admin = new Admin();
                    admin.setId(adminInfo.getAdminId());
                    admin = adminMapper.getAdmin(admin);
                    adminInfo.setUserName(admin.getUsername());
                    adminInfo.setPassword(admin.getPassword());
                }
            }
            System.out.println(adminInfos);
            map.put("adminInfos",adminInfos);
            map.put("result",200);
            map.put("message","成功");
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
