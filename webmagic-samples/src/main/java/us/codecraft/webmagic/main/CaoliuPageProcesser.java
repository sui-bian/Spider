package us.codecraft.webmagic.main;

import com.ning.http.client.ProxyServer;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.dao.AnjukeDao;
import us.codecraft.webmagic.model.LsmSpider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.vo.AnjukeVo;
import us.codecraft.webmagic.vo.CaoliuVo;
import us.codecraft.webmagic.dao.CaoliuDao;

import java.util.ArrayList;
import java.util.List;

public class CaoliuPageProcesser implements PageProcessor {

    private Site site = Site.me().setDomain("caoliu2014.com")
            .addStartUrl("http://caoliu2014.com/thread0806.php?fid=15&search=&page=1").setSleepTime(3000)
            .addHeader("Cookie","CNZZDATA950900=cnzz_eid%3D121532405-1412608782-%26ntime%3D1412671135; 227c9_lastfid=2; 227c9_ck_info=%2F%09; 227c9_winduser=BQhXAwE6BVUGBlxRV1EOBVBRUAYFClNRUQFcA1BRVgILU1BUUAg%3D; 227c9_groupid=8; 227c9_lastvisit=0%091412671611%09%2Fthread0806.php%3Ffid%3D2%26search%3D%26page%3D101")
            .addHeader("Host","caoliu2014.com")
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.2; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0")
            .addHeader("Connection","keep-alive")
            /*.addHeader("Host","caoliu2014.com")
            .addCookie("227c9_lastfid","2")
            .addCookie("expires","Wed, 07-Oct-2015 08:47:03 GMT")
            .addCookie("path","/")
            .addCookie("227c9_lastvisit","0%091412671623%09%2Fthread0806.php%3Ffid%3D2%26search%3D%26page%3D110")*/
            ;

    private CaoliuDao dao = new CaoliuDao();
    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        //List<String> tlinks = html.xpath("//div[@class='pages']/a[@style='font-weight:bold']/@href").all();
        String pagenum = html.xpath("//div[@class='pages']/b/text()").get();
        pagenum=pagenum.trim();
        //page.addTargetRequest(tlinks.get(1));

        String s = html.xpath("//tr[@class='tr3 t_one']/td[@style='text-align:left;padding-left:8px']/h3/a/text()").get();

            List<String> titles = html.xpath("//tr[@class='tr3 t_one']/td[@style='text-align:left;padding-left:8px']/h3/a/text()").all();
            List<String> links = html.xpath("//tr[@class='tr3 t_one']/td[@style='text-align:left;padding-left:8px']/h3/a/@href").all();
            List<String> reply = html.xpath("//tr[@class='tr3 t_one']/td[@class='tal f10 y-style']/text()").all();
            List<CaoliuVo> result = new ArrayList<CaoliuVo>();

            if(titles.size()==links.size()){
                for(int i=0;i<titles.size();i++){
                    if(titles.get(i)==null||titles.get(i).equals(""))
                        continue;
                    try{
                        if(i>reply.size()){
                            result.add(new CaoliuVo(titles.get(i),links.get(i),0,Integer.parseInt(pagenum+600)));
                        }else{
                            result.add(new CaoliuVo(titles.get(i),links.get(i),Integer.parseInt(reply.get(i)),Integer.parseInt(pagenum+600)));
                        }

                    }catch (Exception e){
                        continue;
                    }

                }
            }
            dao.batchinsert(result);

        }
        /*page.putField("content", page.gretHtml().$("div.content").toString());
        page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());*/

    @Override
    public Site getSite() {
        return site;

    }

    public static void main(String[] args) {
        /*Spider.create(new CaoliuPageProcesser())
                .pipeline(new ConsolePipeline()).thread(5).runAsync();*/
        final int PAGESIZE = 305;
        Request[] requests = new Request[PAGESIZE];
        for(int i=0;i<PAGESIZE;i++){
            requests[i] = new Request("http://caoliu2014.com/thread0806.php?fid=15&search=&page="+(i+1));
        }
        LsmSpider.create(new CaoliuPageProcesser()).setProxys(addProxyServers()).addRequest(requests).pipeline(new ConsolePipeline()).thread(5).runAsync();

    }

    private static List<ProxyServer> addProxyServers(){
        List<ProxyServer> proxys = new ArrayList<ProxyServer>();
        ProxyServer p1 = new ProxyServer("118.244.239.2",3128);
        ProxyServer p2 = new ProxyServer("222.246.232.55",80);
        ProxyServer p3 = new ProxyServer("125.39.66.75",80);
        ProxyServer p4 = new ProxyServer("211.167.105.69",80);
        ProxyServer p5 = new ProxyServer("122.96.59.104",80);
        proxys.add(p1);
        proxys.add(p2);
        proxys.add(p3);
        proxys.add(p4);
        proxys.add(p5);
        return proxys;
    }
}