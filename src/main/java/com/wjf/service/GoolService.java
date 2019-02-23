package com.wjf.service;

import com.wjf.mapper.ExpressMapper;
import com.wjf.mapper.GoolMapper;
import com.wjf.po.Express;
import com.wjf.po.Gool;
import com.wjf.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoolService {
    SqlSession sqlSession = null;
    public Map getGool(Gool order){
        Gool gool = new Gool();
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            GoolMapper goolMapper = sqlSession.getMapper(GoolMapper.class);
            List<Gool> gools = goolMapper.getGool(order);

            map.put("express",gools);
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

    public Map updateGool(Gool order){
        Gool gool = new Gool();
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            GoolMapper goolMapper = sqlSession.getMapper(GoolMapper.class);
            goolMapper.updateGool(order);

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

    public Map updateGoolByexpressCode(Gool order, Express order2){
        Gool gool = new Gool();
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            ExpressMapper expressMapper = sqlSession.getMapper(ExpressMapper.class);
            order2 = expressMapper.getExpressByExpressCode(order2);
            System.out.println(order2);
            order2.setExpressType("未发货");
            expressMapper.updateExpress(order2);

            order.setGoolId(order2.getGoolId());
            GoolMapper goolMapper = sqlSession.getMapper(GoolMapper.class);
            goolMapper.updateGool(order);

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
    public Map deleteGool(Gool order){
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            GoolMapper manageMapper = sqlSession.getMapper(GoolMapper.class);
            int manages = manageMapper.deleteGool(order.getGoolId());
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
