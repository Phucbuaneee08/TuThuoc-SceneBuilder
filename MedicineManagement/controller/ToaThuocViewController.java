package MedicineManagement.controller;

import MedicineManagement.model.Product;
import MedicineManagement.model.ToaThuoc;
import MedicineManagement.save.SaveToExcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ToaThuocViewController{
    public int lastIndexToa = 0 ;
    @FXML
    private VBox vbToaThuoc ;
    @FXML private  Button btnToaThuoc;
    public ArrayList<ToaThuoc> listToa;
    private final TuThuocController tuThuocController;
    public ToaThuocViewController(Controller controller, TuThuocController tuThuocController) throws IOException {
        this.listToa =  new SaveToExcel().getToaThuocFromExcel();
        FXMLLoader pane = new FXMLLoader(getClass().getResource("/MedicineManagement/View/ToaThuoc.fxml"));
        pane.setController(this);
        AnchorPane anchorPane = pane.load();
        controller.setToaThuocView(anchorPane);
        btnToaThuoc.setOnAction(event -> showAddToaThuoc());
        this.tuThuocController = tuThuocController;
        this.showList();
    }
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
        return FXCollections.observableArrayList(tuThuocController.main.getListThuoc());
    }
    // lay list tu tu thuoc
    @FXML
    public void showAddToaThuoc(){
        AddToaThuoc addToaThuoc = new AddToaThuoc(this);
        addToaThuoc.showStage();
    } //show stage add toa

    public void thongBaoUongThuoc(){
        int soToaDangTrongThoiGianUong = 0;
        for(ToaThuoc x : this.listToa){
            if(x.status() == 0){
                soToaDangTrongThoiGianUong ++;
            }
        }
        if(soToaDangTrongThoiGianUong != 0) {
            String context = "Bạn đang có " +soToaDangTrongThoiGianUong +" toa (đơn) thuốc đang trong thời gian thực hiện vui lòng kiểm tra trong phần TOA THUỐC (toa màu xanh)";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cảnh báo thuốc");
            alert.setContentText(context);
            alert.showAndWait();
        }
    }

}
