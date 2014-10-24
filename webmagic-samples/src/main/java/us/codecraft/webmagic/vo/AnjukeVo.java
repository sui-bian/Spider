package us.codecraft.webmagic.vo;

/**
 * Created by simonliu on 2014/8/2.
 */
public class AnjukeVo{
    String title;
    String link;
    String price;
    public AnjukeVo(){}

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public AnjukeVo(String title,String link,String price){
        this.title = title;
        this.link = link;
        this.price = price;
    }

    @Override
    public String toString() {
        return "AnjukeVo{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", price=" + price +
                '}';
    }
}
