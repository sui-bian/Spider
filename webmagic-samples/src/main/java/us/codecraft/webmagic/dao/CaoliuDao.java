package us.codecraft.webmagic.dao;

import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.mybatis.MybatisSessionFactory;
import us.codecraft.webmagic.vo.AnjukeVo;
import us.codecraft.webmagic.vo.CaoliuVo;

import java.util.List;

/**
 * Created by simonliu on 2014/8/2.
 */
public class CaoliuDao {

    SqlSession sqlSession;

    public CaoliuDao(){
        sqlSession = MybatisSessionFactory.getSession();
    }

    public void insert(CaoliuVo vo){
        if(sqlSession==null){
            sqlSession =  MybatisSessionFactory.getSession();
        }
        try{
            sqlSession.insert("insertCL",vo);
            sqlSession.commit();
        }catch(Exception e){
            System.out.print(e.getMessage());
        }

    }

    public void batchinsert(List<CaoliuVo> list){
        if(sqlSession==null){
            sqlSession =  MybatisSessionFactory.getSession();
        }
        try{
            sqlSession.insert("batchInsertCL",list);
            sqlSession.commit();
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
    }


    public static void main(String[] args){

    }
}
