package Prj2.model;

import Prj2.Service.CrawlInfo;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Thuoc extends Product {
    private int quantity;

    public Thuoc(int productID, String name,String unit,int quantity, LocalDate expiredDate, String effect) {
        super(productID, name, unit);
        this.expiredDate = expiredDate;
        this.effect = effect;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    private LocalDate expiredDate;
    private String effect;
    private String link;

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

    public int status(){
        int x ;
        LocalDate dt = LocalDate.now();
        Calendar c = Calendar.getInstance();
        c.setTime(Date.from(dt.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        c.add(Calendar.DATE, 10);
        LocalDate nextMonth = c.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if(this.expiredDate.compareTo(dt) < 0){
            x = -1;
        } else if (this.expiredDate.compareTo(nextMonth) < 0){
            x = 0;
        } else {
            x = 1;
        }
        return x;
    }
}