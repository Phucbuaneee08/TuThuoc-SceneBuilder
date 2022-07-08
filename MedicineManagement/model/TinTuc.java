package MedicineManagement.model;

public class TinTuc {
    private String url;
    private String name;
    private String des;
    private String link;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public TinTuc(String url, String name, String des,String link) {
        this.url = url;
        this.name = name;
        this.des = des;
        this.link = link;
    }
}
