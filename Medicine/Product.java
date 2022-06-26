package Medicine;

public abstract class Product {
    private int productID;
    private String name;

    public Product() {
    }

    public Product(int productID, String name, int quantity, String link, String unit) {
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.link = link;
        this.unit = unit;
    }

    private int quantity;
    private String link;
    private String unit;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public void showInfo(){
        System.out.println("ID: "+this.getProductID());
        System.out.println("Name: "+this.getName());
        System.out.println("Quantity: "+this.getQuantity());
        System.out.println("Link: "+this.getLink());
        System.out.println("Unit: "+this.getUnit());
    }
    public boolean checkType(Product x){
        if (x instanceof Thuoc){
            return true;
        }
        return false;
    }
}
