package MedicineManagement.controller;

import MedicineManagement.model.Product;
import MedicineManagement.model.ThuocTrongToa;
import MedicineManagement.model.ToaThuoc;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddToaThuoc extends AddAbstractClass {
    private final Stage stage;
    private final ToaThuocViewController toaThuocViewController;
    private final ToaThuoc toaThuoc = new ToaThuoc();
    @FXML VBox vbThemThuoc;
    @FXML TextField tfName;
    @FXML DatePicker tfDateEnd;
    @FXML DatePicker tfDateStart;
    @FXML Button btnThemToa;
    @FXML Button btnThemThuoc;
    public AddToaThuoc(ToaThuocViewController toaThuocViewController) {
        this.toaThuocViewController = toaThuocViewController;
        stage = new Stage();
        this.loadStage();
        btnThemThuoc.setOnAction(event -> actionThemThuoc());
        btnThemToa.setOnAction(event -> actionAdd());
    }
    public void showStage(){
        super.showStage(this.stage);
    }
    @Override
    public void loadStage() {
        super.loadStage("/MedicineManagement/View/AddToaThuoc.fxml",this.stage);
    }
    @FXML
    void actionThemThuoc() {
        vbThemThuoc.setSpacing(10);
        Text text = new Text("Liều Thuốc");
        TextField textField = new TextField();
        ComboBox<Product> comboBox = new ComboBox<>();
        Callback<ListView<Product>, ListCell<Product>> cellFactory = new Callback<>() {
            @Override
            public ListCell<Product> call(ListView<Product> l) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Product item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getProductID() + ". " + item.getName());
                        }
                    }
                };
            }
        };
        comboBox.setCellFactory(cellFactory);
        comboBox.setButtonCell(comboBox.getCellFactory().call(null));
        comboBox.setItems(this.toaThuocViewController.getListThuocFromTuThuoc());
        Button removeBtn = new Button("-");
        removeBtn.setOnAction((actionEvent) -> vbThemThuoc.getChildren().remove(removeBtn.getParent()));
        HBox hbox = new HBox(comboBox,text,textField,removeBtn);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(5,5,5,5));
        vbThemThuoc.getChildren().add(hbox);
    }
    @Override
    public void actionAdd(){
        String name = tfName.getText();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDateEnd = tfDateEnd.getValue();
        LocalDate localDateStart = tfDateStart.getValue();
        Date dateStart = Date.from(localDateStart.atStartOfDay(defaultZoneId).toInstant());
        Date dateEnd = Date.from(localDateEnd.atStartOfDay(defaultZoneId).toInstant());
        for(Node x : vbThemThuoc.getChildren()) {
            Product product = ((ComboBox<Product>) ((HBox) x).getChildren().get(0)).getValue();
            ThuocTrongToa t = new ThuocTrongToa(toaThuoc.getListProduct().size()+1,product.getName(), product.getUnit(), ((TextField) ((HBox) x).getChildren().get(2)).getText());
            toaThuoc.getListProduct().add(t);
        }
        toaThuoc.setPresID(toaThuocViewController.lastIndexToa+1);
        toaThuoc.setEndDate(dateEnd);
        toaThuoc.setStartedDate(dateStart);
        toaThuoc.setName(name);
        toaThuocViewController.addToa(toaThuoc);
        stage.close();
    }
}
