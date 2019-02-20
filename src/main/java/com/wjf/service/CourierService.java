package com.wjf.service;

import com.wjf.mapper.CourierMapper;
import com.wjf.po.Courier;
import com.wjf.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourierService {
    SqlSession sqlSession = null;

    //添加
    public Map register(Courier order){
        List<Courier> couriers = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            CourierMapper courierMapper = sqlSession.getMapper(CourierMapper.class);

            couriers = courierMapper.getCourier(order);

            if (couriers.size() != 0){
                map.put("message","已有此用户名");
                map.put("result",400);
            }else {
                courierMapper.insertCourier(order);
                if (order.getCourierId() != null){
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
    public Map updateCourier(Courier order){
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            CourierMapper courierMapper = sqlSession.getMapper(CourierMapper.class);
            courierMapper.updateCourier(order);
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
    public Map getCourier(Courier order){
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            CourierMapper courierMapper = sqlSession.getMapper(CourierMapper.class);
            List<Courier> couriers = courierMapper.getCourier(order);
            map.put("couriers",couriers);
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
