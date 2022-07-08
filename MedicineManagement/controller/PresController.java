package MedicineManagement.controller;

import MedicineManagement.model.Product;
import MedicineManagement.model.ThuocTrongToa;
import MedicineManagement.model.ToaThuoc;
import MedicineManagement.save.ReadExcelFileDemo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PresController implements Initializable {
    public int lastIndexToa = 0 ;
    public PresController(Controller controller) throws IOException {
        this.listToa =  new ReadExcelFileDemo().getToaThuocFromExcel();
        FXMLLoader pane = new FXMLLoader(getClass().getResource("/MedicineManagement/View/ToaThuoc.fxml"));
        pane.setController(this);
        controller.setToaThuocView(pane.load());
        this.controller = controller;
        this.showList();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnToaThuoc.setOnAction(event -> showAddToaThuoc(event));
    }
    @FXML
    private VBox vbToaThuoc ;
    @FXML private  Button btnToaThuoc;
    private Controller controller;
    public Date date = new Date();
    private ArrayList<Product> listThuoc = new ArrayList<>();

    public ArrayList<ToaThuoc> listToa = new ArrayList<>();

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
        ObservableList<Product> listThuocTrongTu = FXCollections.observableArrayList(controller.main.getListThuoc());
        return listThuocTrongTu;
    }
    // lay list tu tu thuoc
    @FXML
    public void showAddToaThuoc(ActionEvent event){
        AddToaThuoc addToaThuoc = new AddToaThuoc(this);
        addToaThuoc.showStage();
    } //show stage add toa

}
