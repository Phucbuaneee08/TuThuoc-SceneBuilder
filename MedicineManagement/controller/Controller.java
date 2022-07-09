package MedicineManagement.controller;

import MedicineManagement.save.SaveToExcel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private GridPane gpCaiDat;
    @FXML private AnchorPane gpTienIch;
    @FXML private AnchorPane apTuThuoc;
    @FXML AnchorPane tienIchView;
    @FXML private AnchorPane gpToaThuoc;
    @FXML AnchorPane toaThuocView;
    @FXML private Button btnCaiDat;
    @FXML private Button btnTienIch;
    @FXML private Button btnToaThuoc;
    @FXML private Button btnTuThuoc;
    @FXML private Label lblStatus;
    @FXML private Label lblStatusMini;
    @FXML private VBox pnlStatus;
    @FXML private ImageView btnClose;
    private ToaThuocViewController toaThuocViewController;
    private TuThuocController tuThuocController;
    @Override
    public void initialize(URL url , ResourceBundle rb)  {
        try {
            tuThuocController = new TuThuocController(this);
            toaThuocViewController = new ToaThuocViewController(this,tuThuocController);
            TinTucController tinTucController = new TinTucController(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tuThuocController.showThongBao();
        toaThuocViewController.thongBaoUongThuoc();
    }
    //Click on button
    @FXML
    private void handleClicks(ActionEvent event) {
        if(event.getSource() == btnTuThuoc){
            lblStatusMini.setText("/home/TuThuoc");
            lblStatus.setText("TỦ THUỐC CỦA BẠN");
            apTuThuoc.toFront();
        }
        if(event.getSource() == btnToaThuoc){
            lblStatusMini.setText("/home/ToaThuoc");
            lblStatus.setText("KHO LƯU TOA THUỐC");
            gpToaThuoc.toFront();
        }
        if(event.getSource() == btnTienIch){
            lblStatusMini.setText("/home/TienIch");
            lblStatus.setText("TIN MỚI TRONG NGÀY");
            gpTienIch.toFront();
        }
        if(event.getSource() == btnCaiDat){
            lblStatusMini.setText("/home/CaiDat");
            lblStatus.setText("SETTINGS");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(37,37,39),CornerRadii.EMPTY,Insets.EMPTY)));
            gpCaiDat.toFront();
        }
    }
    @FXML
    private void handleClose(javafx.scene.input.MouseEvent event){
        if(event.getSource()==btnClose){
            try {
                SaveToExcel excel = new SaveToExcel();
                excel.setExcelList(new ArrayList<>(this.tuThuocController.main.getList()));
                excel.setToaThuoc(new ArrayList<>(toaThuocViewController.listToa));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.exit(0);
        }
    }
    public void setToaThuocView(AnchorPane x) {
        this.toaThuocView.getChildren().setAll(x);
    }
    public void setTienIchView(AnchorPane x) {
        this.tienIchView.getChildren().setAll(x);
    }
    public void setTuThuocView(GridPane x){
        this.apTuThuoc.getChildren().addAll(x);
    }
}
