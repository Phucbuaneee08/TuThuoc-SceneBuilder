package MedicineManagement.model;

public class ThuocTrongToa extends Product {

    public String getLieu() {
        return lieu;
    }

    public ThuocTrongToa(int productID,String name,String unit, String lieu) {
        super(productID,name,unit);
        this.lieu = lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    private String lieu;
}
