package us.codecraft.webmagic.model.samples;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

/**
 * Created by simonliu on 2014/8/9.
 */
@TargetUrl("http://beijing.anjuke.com/community/W0QQpZ\\d+")
public class ShuimuVo {
    public List<String> getTitle() {
        return titles;
    }

    public void setTitle(List<String> title) {
        this.titles = title;
    }

    @ExtractBy(value = "//div[@class='t_b']/a[@class='t']/text()"
            , multi = true, notNull = false)
    public List<String> titles;

    @Override
    public String toString(){
        return "title:"+titles;
    }

}
