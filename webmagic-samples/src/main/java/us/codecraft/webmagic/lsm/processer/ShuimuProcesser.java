package us.codecraft.webmagic.lsm.processer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.dao.CaoliuDao;
import us.codecraft.webmagic.dao.LinksDao;
import us.codecraft.webmagic.lsm.model.LinksVo;
import us.codecraft.webmagic.lsm.model.ShuimuVo;
import us.codecraft.webmagic.lsm.proxys.Proxy;
import us.codecraft.webmagic.model.LsmSpider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.MultiPagePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.vo.CaoliuVo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by simonliu on 2014/10/21.
 */
public class ShuimuProcesser implements PageProcessor {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private Site site = Site.me().setDomain("www.newsmth.net")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0")
            ;

    private List<LinksVo> result = new ArrayList<LinksVo>();

    private LinksDao dao = new LinksDao();
    @Override
    public void process(Page page) {
        logger.info("process start : {}",page.getUrl().get());

        Html html = page.getHtml();

        if(page.getUrl().regex("http://www.newsmth.net/nForum/article/*").get()!=null){
            //TODO
            logger.info("link is "+page.getUrl().get());
            String title = html.xpath("//div[@class='b-head corner']/span[@class='n-left']/text()").get();
            String author = html.xpath("//span[@class='a-u-name']/a/text()").get();
            String content = html.xpath("//td[@class='a-content']/p[1]/text()").get();
            List<String> imgLinks = html.xpath("//td[@class='a-content']//img/@src").all();
            ShuimuVo vo = new ShuimuVo(title,author,content,imgLinks);
            logger.info("result is {}",vo);

            LinksVo lvo = new LinksVo(vo);
            lvo.setLink(page.getUrl().toString());


            dao.update(lvo);

        }else{
            //添加url
            List<String> tlinks = html.xpath("//table[@class='board-list tiz']/tbody/tr/td[1]/a/@href").all();
            String nextpage = html.xpath("//ol[@class='page-main']//a[@title='下一页']/@href").get();
            tlinks.add(nextpage);
            addUrl(tlinks,page);


            String currentpage = html.xpath("//ol[@class='page-main']//a[@title='当前页']/text()").get();
            List<String> reply = html.xpath("//table[@class='board-list tiz']/tbody/tr/td[7]/text()").all();
            List<LinksVo> linksVoList = new ArrayList<LinksVo>();

            if(tlinks.size()==reply.size()+1){
                //多最后一个currentpage
                for(int i=0;i<reply.size();i++){
                    LinksVo v = new LinksVo();
                    v.setLink(tlinks.get(i));
                    v.setReply(Integer.parseInt(reply.get(i)));
                    v.setDomain("http://www.newsmth.net");
                    v.setExtra(currentpage);
                    linksVoList.add(v);
                }
            }else if(tlinks.size()!=0&&reply.size()!=0){
                int size = (tlinks.size()-1)<reply.size()?(tlinks.size()-1):reply.size();
                for(int i=0;i<size;i++){
                    LinksVo v = new LinksVo();
                    v.setLink(tlinks.get(i));
                    v.setReply(Integer.parseInt(reply.get(i)));
                    v.setDomain("http://www.newsmth.net");
                    v.setExtra(currentpage);
                    linksVoList.add(v);
                }
            }else{

            }

            dao.batchinsert(linksVoList);
            //批量更新完毕
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        final int PAGESIZE =1;

        Request[] requests = new Request[PAGESIZE];

        for(int i=0;i<PAGESIZE;i++){
            requests[i] = new Request("http://www.newsmth.net/nForum/board/MyPhoto?ajax&p=78");
        }

        LsmSpider.create(new ShuimuProcesser()).pipeline(new ConsolePipeline()).setProxys(Proxy.addProxyServers()).addRequest(requests).thread(5).runAsync();


    }

    public void addUrl(List<String> list,Page page){
        logger.info("add target links size :{}",list.size());
        page.addTargetRequests(list);
    }


}
