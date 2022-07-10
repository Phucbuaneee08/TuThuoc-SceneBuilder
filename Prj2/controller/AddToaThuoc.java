package Prj2.controller;

import Prj2.model.Product;
import Prj2.model.ThuocTrongToa;
import Prj2.model.ToaThuoc;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AddToaThuoc implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private int soThuoc = 0;
    private Controller controller;
    private Stage stage;
    private ToaThuocViewController toaThuocViewController;
    private ToaThuoc toaThuoc = new ToaThuoc();
    @FXML VBox vbThemThuoc;
    @FXML TextField tfName;
    @FXML DatePicker tfDateEnd;
    @FXML DatePicker tfDateStart;
    @FXML Button btnThemToa;
    @FXML Button btnThemThuoc;
    public AddToaThuoc(ToaThuocViewController toaThuocViewController) {
        this.toaThuocViewController = toaThuocViewController;
        stage = new Stage();
        try {
            FXMLLoader parent =new FXMLLoader((getClass().getResource("/Prj2/View/AddToaThuoc.fxml")));
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
        this.soThuoc++;
        TextField textField = new TextField();
        textField.setPromptText("Liều lượng");
        textField.setPrefSize(78,28);
        ComboBox<Product> comboBox = new ComboBox<>();
        comboBox.setPrefSize(152, 28);
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
        comboBox.setItems(this.toaThuocViewController.getListThuocFromTuThuoc());
        Button removeBtn = new Button("-");
        removeBtn.setPrefSize(22,26);
        removeBtn.setOnAction((actionEvent) -> {
            vbThemThuoc.getChildren().remove(removeBtn.getParent());
            this.soThuoc--;
        });
        HBox hbox = new HBox();
        hbox.setPrefSize(301, 34);
        HBox.setMargin(textField, new Insets(3,14,0,14));
        HBox.setMargin(comboBox, new Insets(3,3,0,0));
        HBox.setMargin(removeBtn, new Insets(3,0,0,0));
        hbox.getChildren().addAll(comboBox,textField,removeBtn);
        
        vbThemThuoc.getChildren().add(hbox);
    }

    @FXML
    public void actionSave(ActionEvent event){
        ArrayList<ThuocTrongToa> listToa1 = new ArrayList<>();
        String name = tfName.getText();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDateEnd = tfDateEnd.getValue();
        LocalDate localDateStart = tfDateStart.getValue();
        Date dateStart = Date.from(localDateStart.atStartOfDay(defaultZoneId).toInstant());
        Date dateEnd = Date.from(localDateEnd.atStartOfDay(defaultZoneId).toInstant());
        toaThuoc = new ToaThuoc(1,name,dateStart,dateEnd,listToa1);
        for(Node x : vbThemThuoc.getChildren()) {
            Product product = ((ComboBox<Product>) ((HBox) x).getChildren().get(0)).getValue();
            ThuocTrongToa t = new ThuocTrongToa(toaThuoc.getListProduct().size()+1,product.getName(), product.getUnit(), ((TextField) ((HBox) x).getChildren().get(1)).getText());
            System.out.println(product.getName());
            toaThuoc.getListProduct().add(t);
        }
        
        toaThuoc.setEndDate(dateEnd);
        toaThuoc.setStartedDate(dateStart);
        toaThuoc.setName(name);
        toaThuocViewController.getListToa().add(toaThuoc);
        stage.close();
    }
}