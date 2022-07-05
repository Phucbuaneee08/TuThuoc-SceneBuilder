package MedicineManagement.model;

import java.util.ArrayList;
import java.util.Date;

public class ToaThuoc {
    private int presID;

    public ToaThuoc(int presID, String name, Date startedDate, Date endDate, ArrayList<Product> listProduct) {
        this.presID = presID;
        this.name = name;
        this.startedDate = startedDate;
        this.endDate = endDate;
        this.listProduct = listProduct;
    }

    private String name;
    private Date startedDate;
    private Date endDate;
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


}
