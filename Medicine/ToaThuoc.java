package Medicine;

import java.util.ArrayList;

public class ToaThuoc {
    private int presID;
    private String name;
    private int quantity;
    private String disease;
    private ArrayList<Product> listProduct = new ArrayList<>();

    public int getPresID() {
        return presID;
    }

    public void setPresID(int presID) {
        this.presID = presID;
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

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

}
