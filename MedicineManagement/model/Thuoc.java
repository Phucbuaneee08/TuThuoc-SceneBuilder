package MedicineManagement.model;

import MedicineManagement.Service.CrawlInfo;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Thuoc extends Product {


    public Thuoc(int productID, String name, int quantity, String link, String unit, Date expiredDate, String effect) {
        super(productID, name, quantity, link, unit);
        this.expiredDate = expiredDate;
        this.effect = effect;
    }

    private Date expiredDate;
    private String effect;

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate=expiredDate;
    }

    public String getEffect()  {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override
    public ArrayList<Text> getInfo() {
        ArrayList<Text> x = super.getInfo();
        CrawlInfo info = null;
        try {
            info = new CrawlInfo(this.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        x.addAll(info.listText);
        return x;
    }
}