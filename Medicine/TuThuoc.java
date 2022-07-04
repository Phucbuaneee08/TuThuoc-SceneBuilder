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
    private int rs = 0;

    public TuThuoc() {
        try {
            ArrayList<Product> excelList = new ReadExcelFileDemo().getExcelFileDemo();
            for(Product t: excelList) {
                list.add(t);
                this.rs++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getRs() {
        return rs;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public ObservableList<Product> getList() {
        return this.list;
    }
    private ObservableList<Product> list = FXCollections.observableArrayList();
}
