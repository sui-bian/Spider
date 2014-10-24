package us.codecraft.webmagic.main;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.dao.AnjukeDao;
import us.codecraft.webmagic.model.LsmSpider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.vo.*;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;

public class OschinaBlogPageProcesser implements PageProcessor {

    private Site site = Site.me().setDomain("beijing.anjuke.com").setUserAgent("\tMozilla/5.0 (Windows NT 6.2; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0")
            .addCookie("aQQ_ajkguid","3043A68A-AC51-7C71-798C-12266BA153B6")
            .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language","\tzh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3")
            .addHeader("Host","beijing.anjuke.com")
            .addHeader("User-Agent","\tMozilla/5.0 (Windows NT 6.2; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0")
            .addHeader("Cookie","_tanx_uctrac_cm_done__=_tanx_uctrac_cm_done__; _google_uctrac_cm_done__=_google_uctrac_cm_done__; _baidu_uctrac_cm_done__=_baidu_uctrac_cm_done__; _qq_uctrac_cm_done__=_qq_uctrac_cm_done__; aQQ_ajkguid=3043A68A-AC51-7C71-798C-12266BA153B6; isp=true; __zpspc=8.6.1407049243.1407049331.2%234%7C%7C%7C%7C%7C; Hm_lvt_c5899c8768ebee272710c9c5f365a6d8=1406979830,1407049242; ctid=14; sessid=D1AF08B9-24E7-DA5D-AC91-63F4972B7F74; lps=http%3A%2F%2Fbeijing.anjuke.com%2Fcommunity%2FW0QQpZ474%7C; twe=2; Hm_lpvt_c5899c8768ebee272710c9c5f365a6d8=1407049331")
            .addStartUrl("http://beijing.anjuke.com/community/W0QQpZ100");

    private AnjukeDao dao = new AnjukeDao();
    @Override
    public void process(Page page) {
        //List<String> links = page.getHtml().links().regex("http://beijing\\.anjuke\\.com/community/W0QQpZ3").all();
        Html html = page.getHtml();
        List<String> tlinks = html.xpath("//a[@class='aNxt']/@href").all();
        for(String link:tlinks){
            page.addTargetRequest(link);
        }



            List<String> titles = html.xpath("//div[@class='t_b']/a[@class='t']/text()").all();
            List<String> links = html.xpath("//div[@class='t_b']/a[@class='t']/@href").all();
            List<String> prices = html.xpath("//span[@class='price']/span[@class='sp1']/text()").all();
            List<AnjukeVo> result = new ArrayList<AnjukeVo>();
        if(titles.size()==links.size()){
            for(int i=0;i<titles.size();i++){
                AnjukeVo vo;
                if(i>=prices.size()){
                    vo = new AnjukeVo(titles.get(i),links.get(i),"");
                }else{
                    vo = new AnjukeVo(titles.get(i),links.get(i),prices.get(i));
                }

                page.putField("ajkvo"+i,vo);
                result.add(vo);
            }
            dao.batchinsert(result);

        }
        /*page.putField("content", page.gretHtml().$("div.content").toString());
        page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());*/
    }

    @Override
    public Site getSite() {
        return site;

    }

    public static void main(String[] args) {
        /*Spider.create(new OschinaBlogPageProcesser())
                .pipeline(new ConsolePipeline()).thread(5).runAsync();*/

        LsmSpider.create(new OschinaBlogPageProcesser()).pipeline(new ConsolePipeline()).thread(5).runAsync();
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The demo stopped!");
        System.out.println("To more usage, try to customize your own Spider!");
        System.exit(0);*/
    }
}