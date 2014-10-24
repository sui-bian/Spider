package us.codecraft.webmagic.lsm.model;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

/**
 * Created by simonliu on 2014/10/20.
 */
@TargetUrl("http://www.newsmth.net/nForum/article/PieLove/\\d+")
@HelpUrl("http://www.newsmth.net/nForum/#!board/PieLove?p=\\d+")
public class Shuimu {

    @ExtractBy("//div[@class='b-head corner']/span[@class='n-left']/text()")
    String title;

    @ExtractBy("//span[@class='a-u-name']/a/text()")
    String author;

    @ExtractBy("//div[@class='a-content']/p/text()")
    String content;

    /*@ExtractBy("img[@class='resizeable']/@src")
    List<String> imgLinks;*/

    @Override
    public String toString() {
        return "Shuimu{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", imgLinks='"  + '\'' +
                '}';
    }
}
