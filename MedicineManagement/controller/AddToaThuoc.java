package MedicineManagement.controller;

import MedicineManagement.model.Product;
import MedicineManagement.model.ThuocTrongToa;
import MedicineManagement.model.ToaThuoc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class AddToaThuoc implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private int soThuoc = 0;
    private Stage stage;
    private PresController presController;
    private ToaThuoc toaThuoc = new ToaThuoc();
    @FXML VBox vbThemThuoc;
    @FXML TextField tfName;
    @FXML DatePicker tfDateEnd;
    @FXML DatePicker tfDateStart;
    @FXML Button btnThemToa;
    @FXML Button btnThemThuoc;
    public AddToaThuoc(PresController presController) {
        this.presController = presController;
        stage = new Stage();
        try {
            FXMLLoader parent =new FXMLLoader((getClass().getResource("/MedicineManagement/View/AddToaThuoc.fxml")));
            parent.setController(this);
            stage.setScene(new Scene(parent.load()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        btnThemThuoc.setOnAction(event -> actionThemThuoc(event));
        btnThemToa.setOnAction(event -> actionSave(event));
    }

    public void showStage(){
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    void actionThemThuoc(ActionEvent event) {
        vbThemThuoc.setSpacing(10);
        this.soThuoc++;
        int index = soThuoc;
        Text text = new Text("Liều Thuốc");
        TextField textField = new TextField();
        ComboBox<Product> comboBox = new ComboBox<>();
        Callback<ListView<Product>, ListCell<Product>> cellFactory = new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> l) {
                return new ListCell<Product>() {
                    @Override
                    protected void updateItem(Product item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getProductID()+". "+item.getName());
                        }
                    }
                } ;
            }
        };
        comboBox.setCellFactory(cellFactory);
        comboBox.setButtonCell(comboBox.getCellFactory().call(null));
        comboBox.setItems(this.presController.getListThuocFromTuThuoc());
        Button removeBtn = new Button("-");
        removeBtn.setOnAction((actionEvent) -> {
            vbThemThuoc.getChildren().remove(removeBtn.getParent());
            this.soThuoc--;
        });
        HBox hbox = new HBox(comboBox,text,textField,removeBtn);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(5,5,5,5));
        vbThemThuoc.getChildren().add(hbox);
    }

    @FXML
    public void actionSave(ActionEvent event){
        String name = tfName.getText();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDateEnd = tfDateEnd.getValue();
        LocalDate localDateStart = tfDateStart.getValue();
        Date dateStart = Date.from(localDateStart.atStartOfDay(defaultZoneId).toInstant());
        Date dateEnd = Date.from(localDateEnd.atStartOfDay(defaultZoneId).toInstant());
        for(Node x : vbThemThuoc.getChildren()) {
            Product product = ((ComboBox<Product>) ((HBox) x).getChildren().get(0)).getValue();
            ThuocTrongToa t = new ThuocTrongToa(toaThuoc.getListProduct().size(),product.getName(), product.getUnit(), ((TextField) ((HBox) x).getChildren().get(2)).getText());
            toaThuoc.getListProduct().add(t);
        }
        toaThuoc.setPresID(presController.lastIndexToa);
        toaThuoc.setEndDate(dateEnd);
        toaThuoc.setStartedDate(dateStart);
        toaThuoc.setName(name);
        presController.addToa(toaThuoc);
        stage.close();
    }
}
