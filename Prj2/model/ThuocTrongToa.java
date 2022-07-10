package Prj2.model;

public class ThuocTrongToa extends Product {

    public String getLieu() {
        return lieu;
    }

    public ThuocTrongToa(int productID,String name,String unit, String lieu) {
        super(productID,name,unit);
        this.lieu = lieu;
    }
    private final String lieu;
}