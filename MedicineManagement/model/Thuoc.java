package MedicineManagement.model;

import MedicineManagement.Service.CrawlInfo;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Thuoc extends Product {
    private int quantity;

    public Thuoc(int productID, String name, String unit, int quantity ,LocalDate expiredDate, String effect) {
        super(productID, name , unit);
        this.quantity = quantity;
        this.expiredDate = expiredDate;
        this.effect = effect;
    }

    private LocalDate expiredDate;
    private String effect;
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
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
        CrawlInfo info;
        try {
            info = new CrawlInfo(this.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        x.add(new Text("Số Lượng: "+ this.quantity));
        x.add(new Text ("Hạn sử dụng: "+ this.getExpiredDate()));
        x.addAll(info.listText);
        return x;
    }

    public ObservableList<TinTuc> getNews() throws IOException {
        CrawlInfo info = new CrawlInfo();
        if(info.timThuoc(this.getName())){
            return info.getTinThuoc(this.getName());
        } else {
            return null;
        }
    }
}