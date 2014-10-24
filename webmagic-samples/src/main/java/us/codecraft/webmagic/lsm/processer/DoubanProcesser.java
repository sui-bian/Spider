package us.codecraft.webmagic.lsm.processer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.dao.LinksDao;
import us.codecraft.webmagic.lsm.model.DoubanVo;
import us.codecraft.webmagic.lsm.model.LinksVo;
import us.codecraft.webmagic.lsm.proxys.Proxy;
import us.codecraft.webmagic.model.LsmSpider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simonliu on 2014/10/23.
 */
public class DoubanProcesser implements PageProcessor {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private Site site = Site.me().setDomain("www.douban.com")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0")
            ;

    private List<LinksVo> result = new ArrayList<LinksVo>();

    private LinksDao dao = new LinksDao();
    @Override
    public void process(Page page) {
        logger.info("process start : {}",page.getUrl().get());

        Html html = page.getHtml();

        if(page.getUrl().regex("http://www.douban.com/group/topic/\\d+").get()!=null){
            //TODO
            logger.info("link is "+page.getUrl().get());
            String title = html.xpath("//div[@id='content']/h1/text()").get();
            String authorlink = html.xpath("//div[@class='user-face']/a/@href").get();
            String author = html.xpath("//div[@class='topic-doc']/h3/span[@class='from']/a/text()").get();
            String posttime = html.xpath("//span[@class='color-green']/text()").get();
            if(posttime==null){
                posttime="";
            }
            String content = html.xpath("//div[@class='topic-content']/p/text()").get();
            List<String> imgLinks = html.xpath("//div[@id='link-report']/div[@class='topic-content']//img/@src").all();
            DoubanVo vo = new DoubanVo(title,author,authorlink,content,posttime,imgLinks);

            logger.info("result is {}",vo);

            LinksVo lvo = new LinksVo(vo);
            lvo.setLink(page.getUrl().toString());

            dao.update(lvo);
            logger.info("update complete!");
        }else{

            List<String> tlinks = html.xpath("//table[@class='olt']/tbody/tr[@class=' ']/td[@class='title']/a/@href").all();
            List<String> reply = html.xpath("//table[@class='olt']/tbody/tr[@class=' ']/td[@class=' ']/text()").all();
            String next = html.xpath("//span[@class='next']/a/@href").get();
            logger.info("next is {}",next);
            String current = html.xpath("//span[@class='thispage']/text()").get();
            logger.info("target links size :{}"+tlinks.size());

            if(next!=null&&!next.equals("")){
                addUrl(next,page);
            }else{
                addUrl(page.getUrl().toString(),page);
            }

            if(tlinks!=null&&tlinks.size()>0){
                addUrl(tlinks,page);
            }

            List<LinksVo> result = new ArrayList<LinksVo>();
            for(int i=0;i<reply.size();i++){
                LinksVo l = new LinksVo();
                l.setLink(tlinks.get(i));
                try{
                    l.setReply(Integer.parseInt(reply.get(i)));
                }catch(Exception e){
                    l.setReply(0);
                }

                l.setDomain("http://www.douban.com/haixiu");
                try{
                    l.setPageno(Integer.parseInt(current));
                }catch(Exception e){
                    l.setPageno(-1);
                }

                l.setExtra("");
                dao.insert(l);
            }

        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        final int PAGESIZE =1;

        Request[] requests = new Request[PAGESIZE];

        requests[0] = new Request("http://www.douban.com/group/haixiuzu/discussion?start=62500");

        Proxy.init();
        LsmSpider.create(new DoubanProcesser()).pipeline(new ConsolePipeline()).setProxys(Proxy.addProxyServers()).addRequest(requests).thread(5).runAsync();


    }

    public void addUrl(List<String> list,Page page){
        logger.info("add target links size :{}",list.size());
        page.addTargetRequests(list);
    }

    public void addUrl(String s,Page page){
        logger.info("add target {}",s);
        page.addTargetRequest(s);
    }
}
