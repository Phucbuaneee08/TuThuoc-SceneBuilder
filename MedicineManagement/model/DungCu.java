package MedicineManagement.model;

public class DungCu extends Product {


    private String use;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DungCu(int productID, String name, String unit, int quantity, String use) {
        super(productID, name, unit);
        this.use = use;
        this.quantity = quantity;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

}