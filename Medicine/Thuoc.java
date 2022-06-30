package Medicine;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Thuoc extends Product{


    public Thuoc(int productID, String name, int quantity, String link, String unit, Date expiredDate, String effect) {
        super(productID, name, quantity, link, unit);
        this.expiredDate = new SimpleObjectProperty<>(expiredDate);
        this.effect = new SimpleStringProperty(effect);
    }

    private SimpleObjectProperty<Date> expiredDate;
    private SimpleStringProperty effect;

    public SimpleObjectProperty<Date> getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate.set(expiredDate);
    }

    public SimpleStringProperty getEffect() {
        return effect;
    }

    public void setEffect(SimpleStringProperty effect) {
        this.effect = effect;
    }
}