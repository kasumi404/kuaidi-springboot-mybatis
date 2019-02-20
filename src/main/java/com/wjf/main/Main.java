package com.wjf.main;

import org.apache.ibatis.session.SqlSession;

import com.wjf.mapper.AdminMapper;
import com.wjf.po.Admin;
import com.wjf.utils.SqlSessionFactoryUtil;


public class Main {

    public static void main(String[] args) throws Exception {

        SqlSession sqlSession = null;

        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);


            sqlSession.commit();

        } catch (Exception e) {
            System.out.println("2"+e);
            sqlSession.rollback();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }

}