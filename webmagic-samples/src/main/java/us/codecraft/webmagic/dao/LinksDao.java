package us.codecraft.webmagic.dao;

import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.lsm.model.LinksVo;
import us.codecraft.webmagic.mybatis.MybatisSessionFactory;
import us.codecraft.webmagic.vo.CaoliuVo;

import java.util.List;

/**
 * Created by simonliu on 2014/8/2.
 */
public class LinksDao {

    SqlSession sqlSession;

    public LinksDao(){
        sqlSession = MybatisSessionFactory.getSession();
    }

    public void insert(LinksVo vo){
        if(sqlSession==null){
            sqlSession =  MybatisSessionFactory.getSession();
        }
        try{
            sqlSession.insert("insertLinks",vo);
            sqlSession.commit();
        }catch(Exception e){
            System.out.print(e.getMessage());
        }

    }

    public void batchinsert(List<LinksVo> list){
        if(sqlSession==null){
            sqlSession =  MybatisSessionFactory.getSession();
        }
        try{
            sqlSession.insert("batchInsertLinks",list);
            sqlSession.commit();
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
    }

    public void update(LinksVo vo){

        try{
            sqlSession.update("updateLinksbyLink",vo);
            sqlSession.commit();
        }catch(Exception e){
            System.out.print(e.getMessage());
        }

    }

    public List<LinksVo> selectbyoffset(Integer offset){

        try{
            List<LinksVo> list = sqlSession.selectList("selectbyoffset",offset);
            return list;
        }catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }

}
