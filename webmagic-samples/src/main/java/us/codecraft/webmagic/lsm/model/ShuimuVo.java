package us.codecraft.webmagic.lsm.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shumeng.liu
 * Date: 14-10-21
 * Time: 下午4:09
 * To change this template use File | Settings | File Templates.
 */
public class ShuimuVo {

    String title;

    String author;

    String content;

    List<String> imgLinks;

    public ShuimuVo(){

    }

    public ShuimuVo(String title,String author,String content,List<String> imgLinks){
        this.title = title;
        this.author = author;
        this.content = content;
        this.imgLinks = imgLinks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImgLinks() {
        return imgLinks;
    }

    public void setImgLinks(List<String> imgLinks) {
        this.imgLinks = imgLinks;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Shuimu{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", imgLinks='"  + imgLinks+'\'' +
                '}';
    }
}
