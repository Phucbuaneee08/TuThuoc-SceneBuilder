package MedicineManagement.controller;

import MedicineManagement.model.*;
import MedicineManagement.save.SaveToExcel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private GridPane gpCaiDat;
    @FXML private AnchorPane gpTienIch;
    @FXML AnchorPane tienIchView;
    @FXML private AnchorPane gpToaThuoc;
    @FXML AnchorPane toaThuocView;
    @FXML private GridPane gpTuThuoc;
    @FXML private Button btnCaiDat;
    @FXML private Button btnTienIch;
    @FXML private Button btnToaThuoc;
    @FXML private Button btnTuThuoc;
    @FXML private TextField tfSearch;
    @FXML private Label lblStatus;
    @FXML private Label lblStatusMini;
    @FXML private VBox pnlStatus;
    @FXML private ComboBox filterBox;
    @FXML private ImageView btnClose;
    //insert data on table view
    @FXML public TableView<Product> table;
    @FXML private TableColumn<Product, Integer> productID;
    @FXML private TableColumn<Product, String> name;
    @FXML private TableColumn<Thuoc, LocalDate> expiredDate;
    @FXML private TableColumn<Product, String> effect;
    @FXML private TableColumn<Product, String> unit;
    @FXML private TableColumn<Product, Integer> quantity;
    @FXML private ChoiceBox<String> choiceBox;
    private ToaThuocViewController toaThuocViewController;
    private TinTucController tinTucController;
    public TuThuoc main = new TuThuoc();


    @Override
    public void initialize(URL url , ResourceBundle rb)  {
        showTuThuoc();
        choiceBox.getItems().add("Thuốc");
        choiceBox.getItems().add("Dụng Cụ");
        choiceBox.setValue("Thuốc");
        filterBox.getItems().add("Thuốc");
        filterBox.getItems().add("Dụng Cụ");
        filterBox.getItems().add("Tất Cả");
        try {
            addButtonToTable();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            toaThuocViewController = new ToaThuocViewController(this);
            tinTucController = new TinTucController(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        showThongBao();
        thongBaoUongThuoc();
    }
    //Click on button
    @FXML
    private void handleClicks(ActionEvent event) {
        if(event.getSource() == btnTuThuoc){
            lblStatusMini.setText("/home/TuThuoc");
            lblStatus.setText("TỦ THUỐC CỦA BẠN");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(37,37,39),CornerRadii.EMPTY,Insets.EMPTY)));
            gpTuThuoc.toFront();
            showThongBao();
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
                excel.setExcelList(new ArrayList<>(this.main.getList()));
                excel.setToaThuoc(new ArrayList<>(toaThuocViewController.listToa));
//                System.out.println(excel.getDiffNumRow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.exit(0);
        }
    }
    //Add Medicine data from button , Save data or clear data

    @FXML
    public void getAddView() {
        String choice = choiceBox.getValue();
        if(choice.compareTo("Thuốc")==0){
            AddMedController medController = new AddMedController(this);
            medController.showStage();
        }
        if(choice.compareTo("Dụng Cụ")==0){
            AddDCController addDC = new AddDCController(this);
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
                    if(cellData.getValue() instanceof Thuoc){
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
        this.filterBox.valueProperty().addListener(((observableValue, oldVal, newVal) ->{
            filteredData.setPredicate(product -> {
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
            });
        }));
        this.tfSearch.textProperty().addListener(((observableValue, oldVal, newVal) ->{
            filteredData.setPredicate(product -> {
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
            });
        } ));
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
                final TableCell<Product, Void> cell = new TableCell<>() {

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
                                AddMedController addMedController = new AddMedController(Controller.this, product);
                                addMedController.setTextField( product.getName(), ((Thuoc) product).getQuantity(), product.getUnit(), ((Thuoc) product).getExpiredDate(), ((Thuoc) product).getEffect());
                                addMedController.showStage();
                            } else if (product instanceof DungCu) {
                                AddDCController addDCController = new AddDCController(Controller.this, product);
                                addDCController.setTextField(product.getName(), ((DungCu) product).getQuantity(), product.getUnit(), ((DungCu) product).getUse());
                                addDCController.showStage();
                            }
                        });
                        //set action for remove button
                        m2.setOnAction(event -> {
                            Product rmProduct = getTableRow().getItem();
                            main.getList().remove(rmProduct);

                            System.out.println(table.getItems());
                        });
                        m3.setOnAction(event -> {
                            Product product = getTableRow().getItem();
                            ShowDetailController showDetailController = new ShowDetailController(Controller.this, product);
                            try {
                                showDetailController.setTextField(product.getInfo());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            showDetailController.showStage();
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
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        table.getColumns().add(colBtn);
    }

    public void showThongBao() {
        int soThuocHetHan = 0;
        int soThuocSapHetHan = 0;
        String thuocHetHan  = "" ;
        String thuocSapHetHan = "" ;
        for(Product x : main.getListThuoc()){
            if(((Thuoc) x).status() == -1){
                soThuocHetHan++;
                thuocHetHan += x.getName() + "\n";
            } else if(((Thuoc) x).status() == 0){
                soThuocSapHetHan++;
                thuocSapHetHan += x.getName() + "\n";
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

    public void thongBaoUongThuoc(){
        int soToaDangTrongThoiGianUong = 0;
        for(ToaThuoc x : toaThuocViewController.listToa){
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

    public void setToaThuocView(AnchorPane x) {
        this.toaThuocView.getChildren().setAll(x);
    }
    public void setTienIchView(AnchorPane x) {
        this.tienIchView.getChildren().setAll(x);
    }
}
