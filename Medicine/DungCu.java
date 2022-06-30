package Medicine;

import javafx.beans.property.SimpleStringProperty;

public class DungCu extends Product{
    private SimpleStringProperty use;

    public DungCu(int productID, String name, int quantity, String link, String unit, String use) {
        super(productID, name, quantity, link, unit);
        this.use = new SimpleStringProperty(use);
    }

    public SimpleStringProperty getUse() {
        return use;
    }

    public void setUse(SimpleStringProperty use) {
        this.use = use;
    }

}