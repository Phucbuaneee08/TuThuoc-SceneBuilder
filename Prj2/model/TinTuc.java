package Prj2.model;

public class TinTuc {
    private final String url;
    private String name;
    private final String des;
    private final String link;

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public String getLink() {
        return link;
    }


    public TinTuc(String url, String name, String des,String link) {
        this.url = url;
        this.name = name;
        this.des = des;
        this.link = link;
    }
}