package Prj2.model;

import java.util.Date;

public class ThuocTrongToa extends Product {

    public String getLieu() {
        return lieu;
    }

    public ThuocTrongToa(Product x, String lieu) {
        this.lieu = lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    private String lieu;
}