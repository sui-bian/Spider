package us.codecraft.webmagic.main;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.lsm.model.Shuimu;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.samples.Douban;
import us.codecraft.webmagic.model.samples.IteyeBlog;
import us.codecraft.webmagic.model.samples.News163;
import us.codecraft.webmagic.model.samples.OschinaBlog;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.MultiPagePipeline;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author code4crafter@gmail.com <br>
 */
public class QuickStarter {

    private static Map<String, Class> clazzMap;

    private static Map<String, String> urlMap;

    private static void init(){
        clazzMap = new LinkedHashMap<String, Class>();
        clazzMap.put("1", OschinaBlog.class);
        clazzMap.put("2", Shuimu.class);
        clazzMap.put("3", Douban.class);
        urlMap = new LinkedHashMap<String, String>();
        urlMap.put("1", "http://my.oschina.net/flashsword/blog");
        urlMap.put("2", "http://www.newsmth.net/nForum/#!board/PieLove?p=1");
        urlMap.put("3", "http://www.douban.com/group/beijing/discussion?start=0/");
    }

    public static void main(String[] args) {
        init();
        String key = null;
        key = readKey(key);
        System.out.println("The demo started and will last 20 seconds...");
        //Start spider
        OOSpider.create(Site.me().addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.2; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0").addStartUrl(urlMap.get(key)), clazzMap.get(key)).pipeline(new MultiPagePipeline()).pipeline(new ConsolePipeline()).runAsync();

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
}