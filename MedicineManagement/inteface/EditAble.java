package MedicineManagement.inteface;

import MedicineManagement.model.Product;

public interface EditAble extends Addable {
    void actionEdit(Product x);
    void setTextField(Product x);
}
