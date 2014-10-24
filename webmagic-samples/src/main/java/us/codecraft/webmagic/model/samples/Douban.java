package us.codecraft.webmagic.model.samples;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by simonliu on 2014/10/19.
 */
@TargetUrl("http://www.douban.com/group/topic/\\d+")
@HelpUrl("http://www.douban.com/group/beijing/discussion?start=\\d+")
public class Douban {

    @ExtractBy("//div[@id='content']/h1/text()")
    String title;

    @ExtractBy("//span[@class='from']/a/text()")
    String author;

    @ExtractBy("//span[@class='from']/a/@href")
    String authorLink;

    @ExtractBy("//div[@class='topic-content']/p/text()")
    String content;

    @Override
    public String toString() {
        return "Douban{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", authorLink='" + authorLink + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
