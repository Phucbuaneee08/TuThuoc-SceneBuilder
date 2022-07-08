package Prj2.model;

public class DungCu extends Product {


    private String use;

    public DungCu(int productID, String name, int quantity, String unit, String use) {
        super(productID, name, quantity, unit);
        this.use = use;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

}