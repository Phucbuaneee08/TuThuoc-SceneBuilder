package Prj2.model;

public class TinTuc {
    private String url;
    private String name;
    private String des;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public void setDes(String des) {
        this.des = des;
    }

    public TinTuc(String url, String name, String des) {
        this.url = url;
        this.name = name;
        this.des = des;
    }
}