package Prj2.inteface;

import Prj2.model.Product;

public interface EditAble extends Addable {
    void actionEdit(Product x);
    void setTextField(Product x);
}