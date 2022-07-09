package MedicineManagement.model;

public class ThuocTrongToa extends Product {
    public ThuocTrongToa(int productID,String name,String unit, String lieu) {
        super(productID,name,unit);
        this.lieu = lieu;
    }
    private final String lieu;
    public String getLieu() {
        return lieu;
    }
}
