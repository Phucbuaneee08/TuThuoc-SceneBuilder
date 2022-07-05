package MedicineManagement.Service;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
        String returnString ="";
        String newLine = System.getProperty("line.separator");
        for(Element e : element)
        {
//            returnString = returnString + e.select("strong").text() + newLine;
//            e.select("strong").remove();
//            returnString = returnString + e.text() + newLine;
            Text strongText = new Text(e.select("strong").text());
            strongText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            e.select("strong").remove();
            Text normalText = new Text(e.text());
            listText.add(strongText);
            listText.add(normalText);
        }

        return listText;
    }

}

