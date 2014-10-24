package us.codecraft.webmagic.lsm.model;

import java.util.List;

/**
 * Created by simonliu on 2014/10/23.
 */
public class DoubanVo {

    String title;

    String author;

    String authorlink;

    String content;

    String posttime;

    List<String> imgLinks;

    public DoubanVo(String title, String author, String authorlink, String content, String posttime, List<String> imgLinks) {
        this.title = title;
        this.author = author;
        this.authorlink = authorlink;
        this.content = content;
        this.posttime = posttime;
        this.imgLinks = imgLinks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorlink() {
        return authorlink;
    }

    public void setAuthorlink(String authorlink) {
        this.authorlink = authorlink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public List<String> getImgLinks() {
        return imgLinks;
    }

    public void setImgLinks(List<String> imgLinks) {
        this.imgLinks = imgLinks;
    }

    @Override
    public String toString() {
        return "Douban{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", imgLinks='"  + imgLinks+'\'' +
                ", authorlink='"  + authorlink+'\'' +
                ", posttime='"  + posttime+'\'' +
                '}';
    }
}
