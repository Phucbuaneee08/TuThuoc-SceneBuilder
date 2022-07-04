package Medicine;

import Medicine.controller.ProductController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
            ArrayList<Product> excelList = new ReadExcelFileDemo().getExcelFileDemo();
            for(Product t: excelList) {
                list.add(t);
                if(t instanceof Thuoc){
                    this.rsThuoc++;
                } else if(t instanceof DungCu){
                    this.rsDC++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public ObservableList<Product> getList() {
        return this.list;
    }
    private ObservableList<Product> list = FXCollections.observableArrayList();
}
