package Prj2;

public class Product {
    int id,quantity;
    String name, effect, unit;
    String hsd;

    public Product(int id, String name, String hsd, String effect, String unit,int quantity) {
        this.id = id;
        this.name = name;
        this.hsd = hsd;
        this.effect = effect;
        this.unit = unit;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setProductID(int id) {
        this.id = id;
    }
    public String getHsd(){
        return hsd;
    }
    
    public void setHsd(String hsd) {
        this.hsd = hsd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getEffect(){
        return effect;
    }
    public void setEffect(String effect) {
        this.effect = effect;
    }

}