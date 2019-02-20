package com.wjf.service;

import com.wjf.mapper.AdminInfoMapper;
import com.wjf.mapper.ExpressMapper;
import com.wjf.mapper.GoolMapper;
import com.wjf.po.AdminInfo;
import com.wjf.po.Express;
import com.wjf.po.Gool;
import com.wjf.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.*;

public class ExpressService {
    Integer number = 0;
    SqlSession sqlSession = null;
    public Map getExpressByAdminId(Express order){
        Express express = new Express();
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            ExpressMapper expressMapper = sqlSession.getMapper(ExpressMapper.class);
            List<Express> expresses = expressMapper.getExpressByAdminId(order);

            AdminInfoMapper adminInfoMapper = sqlSession.getMapper(AdminInfoMapper.class);
            for (int i = 0;i< expresses.size();i++){
                AdminInfo adminInfo = new AdminInfo();
                adminInfo.setAdminId(expresses.get(i).getExpressFromId());
                expresses.get(i).setExpressFromInfo(adminInfoMapper.getAdminInfo(adminInfo).get(0));
                adminInfo.setAdminId(expresses.get(i).getExpressToId());
                expresses.get(i).setExpressToInfo(adminInfoMapper.getAdminInfo(adminInfo).get(0));
            }
            map.put("express",expresses);
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

    //App申请寄快递
    public Map insertExpress(Express order, AdminInfo order2, Gool order3){
        Express express = new Express();
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            AdminInfoMapper adminInfoMapper = sqlSession.getMapper(AdminInfoMapper.class);

            GoolMapper goolMapper = sqlSession.getMapper(GoolMapper.class);
            goolMapper.insertGool(order3);
            order.setGoodId(order3.getGoolId());

            number++;
            order.setExpressCode(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+String.format("%03d", number));
            if (adminInfoMapper.getAdminInfo(order2) != null){
                order.setExpressToId(adminInfoMapper.getAdminInfo(order2).get(0).getAdminId());
            }else {
                adminInfoMapper.insertAdminInfo(order2);
                order.setExpressToId(order2.getAdminId());

            }

            ExpressMapper expressMapper = sqlSession.getMapper(ExpressMapper.class);
            expressMapper.insertExpress(order);

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
            return map;
        }
    }
    public Map getExpress(Express order,AdminInfo order2){
        Map<String, Object> map = new HashMap<>();
        try{
            sqlSession = SqlSessionFactoryUtil.openSession();
            ExpressMapper expressMapper = sqlSession.getMapper(ExpressMapper.class);
            List<Express> expresseList = new ArrayList<>();
            if (order2.getAdminName()!=null&&!order2.getAdminName().equals("")){
                AdminInfoMapper adminInfoMapper = sqlSession.getMapper(AdminInfoMapper.class);
                List<AdminInfo> adminInfos = adminInfoMapper.getAdminInfo(order2);

                for (AdminInfo adminInfoOne : adminInfos){

                    order.setExpressFromId(adminInfoOne.getAdminId());
                    order.setExpressToId(adminInfoOne.getAdminId());
                    List<Express> expresses = expressMapper.getExpressByAdminId(order);
                    for (int i = 0;i< expresses.size();i++){
                        AdminInfo adminInfo = new AdminInfo();
                        adminInfo.setAdminId(expresses.get(i).getExpressFromId());
                        expresses.get(i).setExpressFromInfo(adminInfoMapper.getAdminInfo(adminInfo).get(0));
                        adminInfo.setAdminId(expresses.get(i).getExpressToId());
                        expresses.get(i).setExpressToInfo(adminInfoMapper.getAdminInfo(adminInfo).get(0));
                        expresseList.add(expresses.get(i));
                    }
                }

                map.put("result",200);
                map.put("message","成功");
            }else {
                expresseList = expressMapper.getExpressByAdminId(order);
                System.out.println(order);
                System.out.println(expresseList);
                map.put("result",200);
                map.put("message","成功");
            }
            map.put("express",expresseList);
        }catch (Exception e){
            System.out.println("error:"+e);
            map.put("result",500);
            map.put("message",e.toString());
            sqlSession.rollback();
        }finally{
            return map;
        }
    }

}
