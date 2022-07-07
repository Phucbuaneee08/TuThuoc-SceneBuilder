package MedicineManagement.Service;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import MedicineManagement.model.TinTuc;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlInfo  {
    public ArrayList<Text> listText = new ArrayList<>() ;

    public CrawlInfo() {
    }

    public CrawlInfo(String name) throws IOException{
        String url = this.findPath(name);
        this.listText = this.crawl(url);
    }
    private String findPath(String name) throws IOException{
        String q = URLEncoder.encode(name, StandardCharsets.UTF_8);
        String url = "https://api-gateway.pharmacity.vn/api/product-search?search="+q;
        String data = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.115 Safari/537.36 OPR/88.0.4412.65")
                .ignoreContentType(true).execute().body();

        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
        String path = json.get("data")
                .getAsJsonObject()
                .get("products")
                .getAsJsonObject()
                .get("edges")
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("node").getAsJsonObject().get("slug").getAsString()+".html";
        return "https://www.pharmacity.vn/"+path;
    }
    private ArrayList<Text> crawl(String url) throws IOException{

        Document doc =Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.115 Safari/537.36 OPR/88.0.4412.65")
                .get();
        Elements element = doc.select("div.ProductTab_content__2H-Vw").first().select("p");
        for(Element e : element)
        {
            Text strongText = new Text(e.select("strong").text());
            strongText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            e.select("strong").remove();
            Text normalText = new Text(e.text());
            listText.add(strongText);
            listText.add(normalText);
        }
        return listText;
    }

    public ObservableList<TinTuc> crawlTinTuc(int page) throws IOException {
        ObservableList<TinTuc> listTinTuc = FXCollections.observableArrayList();
        String url = "https://vinmec.com/vi/tin-tuc/?page="+Integer.toString(page);
        Document doc = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.115 Safari/537.36 OPR/88.0.4412.65")
                .get();
        Elements urls = doc.select("div.post-list > ul >  li");
        for(Element x: urls){
            String url1 = x.select("a img").attr("v-lazy").replace("'","");
            String name = x.select("h2 a").text();
            x.select("h2 a").remove();
            String des = x.select("div.post-content").text();
            TinTuc tinTuc = new TinTuc(url1,name,des);
            listTinTuc.add(tinTuc);
        }
        return listTinTuc;
    }
}

