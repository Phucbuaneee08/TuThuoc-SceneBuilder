package Prj2.model;

import Prj2.save.SaveToExcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.io.IOException;
import java.util.ArrayList;


public class ListToaThuoc {


    public ObservableList<ToaThuoc> getListToa() {
        return this.list;
    }
    public ObservableList<ToaThuoc> getListThuoc() {

        for(ToaThuoc x : this.list) {
         
                this.listToaThuoc.add(x);
      
        }
        return this.listToaThuoc;
    }
   
    private ObservableList<ToaThuoc> list = FXCollections.observableArrayList();
    private ObservableList<ToaThuoc> listToaThuoc = FXCollections.observableArrayList();
}