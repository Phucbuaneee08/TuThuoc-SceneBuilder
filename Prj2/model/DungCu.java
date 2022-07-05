package Prj2.model;

import javafx.beans.property.SimpleStringProperty;

public class DungCu extends Product{


    private String use;

    public DungCu(int productID, String name, int quantity, String link, String unit, String use) {
        super(productID, name, quantity, link, unit);
        this.use = use;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

}