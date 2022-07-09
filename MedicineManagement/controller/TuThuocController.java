package MedicineManagement.controller;

import MedicineManagement.model.DungCu;
import MedicineManagement.model.Product;
import MedicineManagement.model.Thuoc;
import MedicineManagement.model.TuThuoc;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TuThuocController implements Initializable {
    @FXML private TextField tfSearch;
    @FXML private Button btnAddmed;
    @FXML private ComboBox filterBox;
    @FXML public TableView<Product> table;
    @FXML private TableColumn<Product, Integer> productID;
    @FXML private TableColumn<Product, String> name;
    @FXML private TableColumn<Thuoc, LocalDate> expiredDate;
    @FXML private TableColumn<Product, String> effect;
    @FXML private TableColumn<Product, String> unit;
    @FXML private TableColumn<Product, Integer> quantity;
    @FXML private ChoiceBox<String> choiceBox;

    public TuThuocController(Controller controller) throws IOException {
        FXMLLoader pane = new FXMLLoader(getClass().getResource("/MedicineManagement/View/TuThuoc.fxml"));
        pane.setController(this);
        GridPane gridPane = pane.load();
        controller.setTuThuocView(gridPane);
        choiceBox.getItems().add("Thuốc");
        choiceBox.getItems().add("Dụng Cụ");
        choiceBox.setValue("Thuốc");
        filterBox.getItems().add("Thuốc");
        filterBox.getItems().add("Dụng Cụ");
        filterBox.getItems().add("Tất Cả");
        btnAddmed.setOnAction(event -> getAddView());
    }
    public TuThuoc main = new TuThuoc();
    @Override
    public void initialize(URL url , ResourceBundle rb)  {
        showTuThuoc();
        addButtonToTable();
    }
    @FXML
    public void getAddView() {
        String choice = choiceBox.getValue();
        if(choice.compareTo("Thuốc")==0){
            AddAbstractClass medController = new AddMedController(this);
            medController.showStage();
        }
        if(choice.compareTo("Dụng Cụ")==0){
            AddAbstractClass addDC = new AddDCController(this);
            addDC.showStage();
        }
    }
    public void showTuThuoc(){
        table.setRowFactory(new Callback<>() {
            @Override
            public TableRow<Product> call(TableView<Product> tableView) {
                return new TableRow<>() {
                    @Override
                    public void updateItem(Product item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item == null){
                            setStyle("");
                        } else if (item instanceof DungCu) {
                            setStyle("");
                        } else if (((Thuoc) item).status() == -1) {
                            setStyle("-fx-background-color: #FF0000;");
                        } else if (((Thuoc) item).status() == 0) {
                            setStyle("-fx-background-color: #FFFF00;");
                        } else {
                            setStyle("");
                        }
                    }
                };
            }
        });
        productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        expiredDate.setCellValueFactory(cellData ->{
                    if(cellData.getValue() != null){
                        return new SimpleObjectProperty<>(cellData.getValue().getExpiredDate());
                    } else {
                        return null;
                    }
                }
        );
        effect.setCellValueFactory(cellData ->{
                    if(cellData.getValue() instanceof Thuoc){
                        return new SimpleStringProperty(((Thuoc) cellData.getValue()).getEffect());
                    } else {
                        return new SimpleStringProperty(((DungCu) cellData.getValue()).getUse());
                    }
                }
        );
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        FilteredList<Product> filteredData;
        filteredData = new FilteredList<>(main.getList(),b->true);
        this.filterBox.valueProperty().addListener(((observableValue, oldVal, newVal) -> filteredData.setPredicate(product -> {
            if(newVal == null || newVal == "Tất Cả"){
                return true;
            }
            else if(newVal == "Thuốc"){
                return product instanceof Thuoc;
            }
            else if(newVal == "Dụng Cụ"){
                return product instanceof DungCu;
            }
            return false;
        })));
        this.tfSearch.textProperty().addListener(((observableValue, oldVal, newVal) -> filteredData.setPredicate(product -> {
            if(newVal == null || newVal.isEmpty()){
                return true;
            }
            String lowerCase =newVal.toLowerCase();
            if(product.getName().toLowerCase().contains(lowerCase)){
                return true;
            }else if(product instanceof Thuoc && filterBox.getValue() == "Thuốc"){
                return ((Thuoc) product).getEffect().toLowerCase().contains(lowerCase);
            } else if(product instanceof DungCu &&  filterBox.getValue() == "Dụng Cụ"){
                return ((DungCu) product).getUse().toLowerCase().contains(lowerCase);
            }
            else{
                return false;
            }
        })));
        SortedList<Product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
    private void addButtonToTable() {
        TableColumn<Product, Void> colBtn = new TableColumn<>("");
        colBtn.setMaxWidth(1200);
        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
                return new TableCell<>() {
                    private final MenuButton btn = new MenuButton("");
                    {
                        MenuItem m1 = new MenuItem("edit");
                        MenuItem m2 = new MenuItem("delete");
                        MenuItem m3 = new MenuItem("detail");
                        btn.getItems().add(m1);
                        btn.getItems().add(m2);
                        btn.getItems().add(m3);
                        //set action for edit button
                        m1.setOnAction(event -> {
                            Product product = getTableRow().getItem();
                            if (product instanceof Thuoc) {
                                AddMedController addMedController = new AddMedController(TuThuocController.this, product);
                                addMedController.setTextField(product);
                                addMedController.showStage();
                            } else if (product instanceof DungCu) {
                                AddDCController addDCController = new AddDCController(TuThuocController.this, product);
                                addDCController.setTextField(product);
                                addDCController.showStage();
                            }
                        });
                        m2.setOnAction(event -> {
                            Product rmProduct = getTableRow().getItem();
                            main.getList().remove(rmProduct);
                        });
                        m3.setOnAction(event -> {
                            Product product = getTableRow().getItem();
                            ShowDetail showDetail = new ShowDetail(product);
                            try {
                                showDetail.setVBox(product.getInfo());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            showDetail.showStage();
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        colBtn.setCellFactory(cellFactory);
        table.getColumns().add(colBtn);
    }
    public void showThongBao() {
        int soThuocHetHan = 0;
        int soThuocSapHetHan = 0;
        StringBuilder thuocHetHan  = new StringBuilder();
        StringBuilder thuocSapHetHan = new StringBuilder();
        for(Product x : main.getListThuoc()){
            if(((Thuoc) x).status() == -1){
                soThuocHetHan++;
                thuocHetHan.append(x.getName()).append("\n");
            } else if(((Thuoc) x).status() == 0){
                soThuocSapHetHan++;
                thuocSapHetHan.append(x.getName()).append("\n");
            }
        }

        if(soThuocHetHan != 0 ||  soThuocSapHetHan != 0){
            String context = "Có " + soThuocSapHetHan + " thuốc sắp hết hạn \n"
                    +thuocSapHetHan+"\n"
                    +"Và "+ soThuocHetHan+ " thuốc đã hết hạn cần được thay thế hoặc loại bỏ \n"
                    + thuocHetHan;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cảnh báo thuốc");
            alert.setContentText(context);
            alert.showAndWait();
        }
    }
}
