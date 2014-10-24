package us.codecraft.webmagic.dao;

import org.junit.Test;
import us.codecraft.webmagic.vo.AnjukeVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simonliu on 2014/8/3.
 */
public class AnjukeDaoTest {

    @Test
    public void insert(){
        AnjukeDao dao = new AnjukeDao();
        dao.insert(new AnjukeVo("aa","bb","cc"));
    }

    @Test
    public void batchinsert(){
        AnjukeDao dao = new AnjukeDao();
        List<AnjukeVo> list = new ArrayList<AnjukeVo>();
        list.add(new AnjukeVo("aa","bb","cc"));
        list.add(new AnjukeVo("aaa","bbb","ccc"));
        dao.batchinsert(list);
    }

    @Test
    public void testSelect(){
        AnjukeDao dao = new AnjukeDao();
        List<AnjukeVo> list = dao.selectAJK();
        System.out.print(list.get(0).getTitle());
    }
}
