package us.codecraft.webmagic.lsm.model;

import java.util.List;

/**
 * Created by simonliu on 2014/10/21.
 */
public class LinksVo {
    public LinksVo(){}

    public LinksVo(ShuimuVo vo){
        this.title = vo.title;
        this.author = vo.author;
        this.content = vo.content;
        this.img = vo.imgLinks.toString();
    }

    public LinksVo(DoubanVo vo){
        this.title = vo.title;
        this.author = vo.author;
        this.content = vo.content;
        this.authorlink = vo.authorlink;
        this.posttime = vo.posttime;
        this.img = vo.imgLinks.toString();
    }

    public LinksVo(String link,String domain,Integer reply,String author,String content,String img){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    String link="";

    String domains;

    Integer reply;

    String extra="";

    String author="";

    String content="";

    String img="";

    String title="";

    String authorlink;

    String posttime;

    Integer pageno;

    public Integer getPageno() {
        return pageno;
    }

    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public String getAuthorlink() {
        return authorlink;
    }

    public void setAuthorlink(String authorlink) {
        this.authorlink = authorlink;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDomain() {
        return domains;
    }

    public void setDomain(String domain) {
        this.domains = domain;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }
}
