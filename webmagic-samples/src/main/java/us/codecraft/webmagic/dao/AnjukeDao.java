package us.codecraft.webmagic.dao;


import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.mybatis.MybatisSessionFactory;
import us.codecraft.webmagic.vo.AnjukeVo;

import java.util.List;

/**
 * Created by simonliu on 2014/8/2.
 */
public class AnjukeDao {

    SqlSession sqlSession;

    public AnjukeDao(){
        sqlSession = MybatisSessionFactory.getSession();
    }

    public void insert(AnjukeVo vo){
        if(sqlSession==null){
            sqlSession =  MybatisSessionFactory.getSession();
        }
        try{
            sqlSession.insert("insertAJK",vo);
            sqlSession.commit();
        }catch(Exception e){
            System.out.print(e.getMessage());
        }

    }

    public void batchinsert(List<AnjukeVo> list){
        if(sqlSession==null){
            sqlSession =  MybatisSessionFactory.getSession();
        }
        try{
            sqlSession.insert("batchInsertAJK",list);
            sqlSession.commit();
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
    }

    public List<AnjukeVo> selectAJK(){
        if(sqlSession==null){
            sqlSession =  MybatisSessionFactory.getSession();
        }
        List<AnjukeVo> list = sqlSession.selectList("selectAJK");
        return list;
    }

    public static void main(String[] args){
        AnjukeDao dao = new AnjukeDao();
        dao.insert(new AnjukeVo("aa","bb","cc"));
        System.out.print("asd");
    }
}
