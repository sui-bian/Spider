package us.codecraft.webmagic.vo;

/**
 * Created by simonliu on 2014/8/2.
 */
public class CaoliuVo {
    String title;
    String link;
    Integer reply;

    public Integer getPagenum() {
        return pagenum;
    }

    public void setPagenum(Integer pagenum) {
        this.pagenum = pagenum;
    }

    Integer pagenum;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public CaoliuVo(){}



    public CaoliuVo(String title, String link, Integer reply,Integer pagenum){
        this.title = title;
        this.link = link;
        this.reply = reply;
        this.pagenum = pagenum;
    }

    @Override
    public String toString() {
        return "AnjukeVo{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", reply=" + reply.toString() +
                '}';
    }
}
