package Medicine;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Thuoc extends Product{


    public Thuoc(int productID, String name, int quantity, String link, String unit, Date expiredDate, String effect) {
        super(productID, name, quantity, link, unit);
        this.expiredDate = expiredDate;
        this.effect = effect;
    }

    private Date expiredDate;
    private String effect;

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }



    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}