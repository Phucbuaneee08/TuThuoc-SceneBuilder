package Prj2.model;

import java.util.ArrayList;
import Prj2.Service.CrawlInfo;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class DungCu extends Product {


    private String use;
    private int quantity;
    public DungCu(int productID, String name, String unit, int quantity, String use) {
        super(productID, name, unit);
        this.use = use;
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }
    @Override
    public ArrayList<Text> getInfo() {
        ArrayList<javafx.scene.text.Text> x = super.getInfo();
        CrawlInfo info;
        try {
            info = new CrawlInfo(this.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        x.add(new Text("Số Lượng: "+ this.quantity));
        x.addAll(info.listText);
        return x;
    }

}