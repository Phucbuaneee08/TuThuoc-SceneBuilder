package MedicineManagement.controller;

import MedicineManagement.model.Product;
import MedicineManagement.model.ToaThuoc;
import MedicineManagement.save.SaveToExcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ToaThuocViewController{
    public int lastIndexToa = 0 ;
    public ToaThuocViewController(Controller controller) throws IOException {
        this.listToa =  new SaveToExcel().getToaThuocFromExcel();
        FXMLLoader pane = new FXMLLoader(getClass().getResource("/MedicineManagement/View/ToaThuoc.fxml"));
        pane.setController(this);
        controller.setToaThuocView(pane.load());
        btnToaThuoc.setOnAction(event -> showAddToaThuoc());
        this.controller = controller;
        this.showList();
    }
    @FXML
    private VBox vbToaThuoc ;
    @FXML private  Button btnToaThuoc;
    private final Controller controller;
    public ArrayList<ToaThuoc> listToa;
    public void addToa(ToaThuoc x){
        lastIndexToa++;
        listToa.add(x);
        Button toaThuoc = new Button(x.getName());
        vbToaThuoc.getChildren().add(toaThuoc);
    }
    public void showList() {
        for(ToaThuoc x : listToa){
            Button toaThuoc = new Button(x.getName());
            vbToaThuoc.getChildren().add(toaThuoc);
            lastIndexToa++;
        }
    }
    public ObservableList<Product> getListThuocFromTuThuoc(){
        return FXCollections.observableArrayList(controller.main.getListThuoc());
    }
    // lay list tu tu thuoc
    @FXML
    public void showAddToaThuoc(){
        AddToaThuoc addToaThuoc = new AddToaThuoc(this);
        addToaThuoc.showStage();
    } //show stage add toa
}
