package MedicineManagement.model;

import javafx.scene.text.Text;

import java.util.ArrayList;

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
    public ArrayList<Text> getInfo(){
        ArrayList<Text> listInfo = new ArrayList<>();
        listInfo.add(0,new Text("Tên sản phẩm: " + this.name));
        listInfo.add(1,new Text("Số lượng: " + this.quantity));
        listInfo.add(2,new Text ("Đơn vị: " + this.unit));
        return listInfo;
    }
}
