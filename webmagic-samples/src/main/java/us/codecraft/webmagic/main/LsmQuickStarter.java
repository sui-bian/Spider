package us.codecraft.webmagic.main;

import com.ning.http.client.ProxyServer;
import us.codecraft.webmagic.model.LsmSpider;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.samples.Douban;
import us.codecraft.webmagic.model.samples.IteyeBlog;
import us.codecraft.webmagic.model.samples.News163;
import us.codecraft.webmagic.model.samples.OschinaBlog;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.MultiPagePipeline;

import java.util.*;

/**
 * @author code4crafter@gmail.com <br>
 */
public class LsmQuickStarter {

    private static Map<String, Class> clazzMap;

    private static Map<String, String> urlMap;

    private static void init(){
        clazzMap = new LinkedHashMap<String, Class>();
        clazzMap.put("1", OschinaBlog.class);
        clazzMap.put("2", IteyeBlog.class);
        clazzMap.put("3", Douban.class);
        urlMap = new LinkedHashMap<String, String>();
        urlMap.put("1", "http://my.oschina.net/flashsword/blog");
        urlMap.put("2", "http://flashsword20.iteye.com/");
        urlMap.put("3", "http://www.douban.com");
    }

    public static void main(String[] args) {
        init();
        String key = null;
        key = readKey(key);
        System.out.println("The demo started and will last 20 seconds...");
        //Start spider
        List<ProxyServer> proxys = addProxyServers();
        LsmSpider.create(Site.me().addStartUrl(urlMap.get(key)), clazzMap.get(key)).setProxys(proxys).pipeline(new MultiPagePipeline()).pipeline(new ConsolePipeline()).thread(15).runAsync();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The demo stopped!");
        System.out.println("To more usage, try to customize your own Spider!");
        System.exit(0);
    }

    private static String readKey(String key) {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Choose a Spider demo:");
        for (Map.Entry<String, Class> classEntry : clazzMap.entrySet()) {
            System.out.println(classEntry.getKey()+"\t" + classEntry.getValue() + "\t" + urlMap.get(classEntry.getKey()));
        }
        while (key == null) {
            key = new String(stdin.nextLine());
            if (clazzMap.get(key) == null) {
                System.out.println("Invalid choice!");
                key = null;
            }
        }
        return key;
    }

    private static List<ProxyServer> addProxyServers(){
        List<ProxyServer> proxys = new ArrayList<ProxyServer>();
        ProxyServer p1 = new ProxyServer("114.32.206.176",8088);
        ProxyServer p2 = new ProxyServer("58.117.149.94",8088);
        ProxyServer p3 = new ProxyServer("222.171.227.35",8088);
        ProxyServer p4 = new ProxyServer("211.87.206.29",8088);
        ProxyServer p5 = new ProxyServer("120.194.70.213",8088);
        ProxyServer p6 = new ProxyServer("218.201.74.115",8123);
        proxys.add(p1);
        proxys.add(p2);
        proxys.add(p3);
        proxys.add(p4);
        proxys.add(p5);
        proxys.add(p6);
        return proxys;
    }
}
