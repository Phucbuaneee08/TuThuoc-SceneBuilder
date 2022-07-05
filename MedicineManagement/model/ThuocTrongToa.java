package MedicineManagement.model;

import java.util.Date;

public class ThuocTrongToa extends Thuoc {
    public ThuocTrongToa(int productID, String name, int quantity, String link, String unit, Date expiredDate, String effect, String lieu) {
        super(productID, name, quantity, link, unit, expiredDate, effect);
        this.lieu = lieu;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    private String lieu;
}
