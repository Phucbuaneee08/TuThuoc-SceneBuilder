package MedicineManagement.model;

import MedicineManagement.save.SaveToExcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class TuThuoc {
    private int rsThuoc = 0;
    private int rsDC = 0;

    public int getRsThuoc() {
        return rsThuoc;
    }

    public void setRsThuoc(int rsThuoc) {
        this.rsThuoc = rsThuoc;
    }

    public int getRsDC() {
        return rsDC;
    }

    public void setRsDC(int rsDC) {
        this.rsDC = rsDC;
    }

    public TuThuoc() {
        try {
            ArrayList<Product> excelList = new SaveToExcel().getExcelFileDemo();
            for(Product t: excelList) {
                list.add(t);
                if(t instanceof Thuoc){
                    this.rsThuoc++;
                } else if(t instanceof DungCu){
                    this.rsDC++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<Product> getList() {
        return this.list;
    }
    public ObservableList<Product> getListThuoc() {

        for(Product x : this.list) {
            if(x instanceof Thuoc){
                this.listThuoc.add(x);
            }
        }
        return this.listThuoc;
    }
    public ObservableList<Product> getListDC() {

        for(Product x : this.list) {
            if(x instanceof DungCu){
                this.listDC.add(x);
            }
        }
        return this.listDC;
    }
    private final ObservableList<Product> list = FXCollections.observableArrayList();
    private final ObservableList<Product> listThuoc = FXCollections.observableArrayList();
    private final ObservableList<Product> listDC = FXCollections.observableArrayList();
}