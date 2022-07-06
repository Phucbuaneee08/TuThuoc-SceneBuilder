package Prj2.model;

import java.util.ArrayList;
import java.util.Date;

public class ToaThuoc {
    private int presID;

    public ToaThuoc() {
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ArrayList<Product> getListProduct() {
        return this.listProduct;
    }

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

    public void showInfo(){

    }
}