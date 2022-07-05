package MedicineManagement.controller;

import MedicineManagement.model.Product;
import MedicineManagement.model.ThuocTrongToa;
import MedicineManagement.model.ToaThuoc;

import java.util.ArrayList;
import java.util.Date;

public class PresController {
    public Date date = new Date();
    public ArrayList<Product> listThuoc = new ArrayList<>();

    ArrayList<ToaThuoc> listToa = new ArrayList<>();
    public void addThuoc(ThuocTrongToa x){
        listThuoc.add(x);
    }

    public void addToa(){
       listToa.add(new ToaThuoc(1,"TEST",date,date,listThuoc));
    }


}
