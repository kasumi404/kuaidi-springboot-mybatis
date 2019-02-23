package com.wjf.service;

import com.wjf.mapper.AdminInfoMapper;
import com.wjf.mapper.ExpressMapper;
import com.wjf.mapper.GoolMapper;
import com.wjf.po.Admin;
import com.wjf.po.AdminInfo;
import com.wjf.po.Express;
import com.wjf.po.Gool;
import com.wjf.utils.QRCodeUtil;
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
            order.setGoolId(order3.getGoolId());

            order.setCourierId(1);

            number++;


            String code = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+String.format("%03d", number);
            QRCodeUtil.zxingCodeCreate(code, 300, 300, "E:/expressCode/"+code+".jpg", "jpg");

            order.setExpressCode(code);
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
            AdminInfoMapper adminInfoMapper = sqlSession.getMapper(AdminInfoMapper.class);
            GoolMapper goolMapper = sqlSession.getMapper(GoolMapper.class);
            if (order2.getAdminName()!=null&&!order2.getAdminName().equals("")){
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

                        Gool gool = new Gool();
                        gool.setGoolId(expresses.get(i).getGoolId());
                        expresses.get(i).setGool(goolMapper.getGool(gool).get(0));
                        System.out.println(gool);
                        System.out.println(goolMapper.getGool(gool));

                    }
                }

                map.put("result",200);
                map.put("message","成功");
            }else {
                expresseList = expressMapper.getExpressByAdminId(order);

                for (int i = 0;i< expresseList.size();i++){
                    AdminInfo adminInfo = new AdminInfo();
                    adminInfo.setAdminId(expresseList.get(i).getExpressFromId());
                    expresseList.get(i).setExpressFromInfo(adminInfoMapper.getAdminInfo(adminInfo).get(0));
                    adminInfo.setAdminId(expresseList.get(i).getExpressToId());
                    expresseList.get(i).setExpressToInfo(adminInfoMapper.getAdminInfo(adminInfo).get(0));

                    Gool gool = new Gool();
                    gool.setGoolId(expresseList.get(i).getGoolId());
                    expresseList.get(i).setGool(goolMapper.getGool(gool).get(0));
                }
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

    public Map updateExpress(Express order,Gool order2) {
        Map<String, Object> map = new HashMap<>();
        try{
            System.out.println(order);
            sqlSession = SqlSessionFactoryUtil.openSession();
            ExpressMapper expressMapper = sqlSession.getMapper(ExpressMapper.class);
            expressMapper.updateExpress(order);
            sqlSession.commit();
            System.out.println(order);
            GoolMapper goolMapper = sqlSession.getMapper(GoolMapper.class);
            goolMapper.updateGool(order2);
            sqlSession.commit();
            map.put("result",200);
            map.put("message","成功");
        }catch (Exception e){
            System.out.println("error:"+e);
            map.put("result",500);
            map.put("message",e.toString());
            sqlSession.rollback();
        }finally{
            return map;
        }
    }
    public Map getExpressByExpressCode(Express order) {
        Map<String, Object> map = new HashMap<>();
        try{
            sqlSession = SqlSessionFactoryUtil.openSession();
            ExpressMapper expressMapper = sqlSession.getMapper(ExpressMapper.class);
            GoolMapper goolMapper = sqlSession.getMapper(GoolMapper.class);
            Express express = expressMapper.getExpressByExpressCode(order);
            Gool gool = new Gool();
            gool.setGoolId(express.getGoolId());
            express.setGool(goolMapper.getGool(gool).get(0));
            map.put("express",express);
            map.put("result",200);
            map.put("message","成功");
        }catch (Exception e){
            System.out.println("error:"+e);
            map.put("result",500);
            map.put("message",e.toString());
            sqlSession.rollback();
        }finally{
            return map;
        }
    }
    public Map deleteExpress(Express order){
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            ExpressMapper manageMapper = sqlSession.getMapper(ExpressMapper.class);
            int manages = manageMapper.deleteExpress(order.getExpressId());
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
