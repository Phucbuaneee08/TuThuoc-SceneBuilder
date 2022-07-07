package MedicineManagement.controller;

import MedicineManagement.model.DungCu;
import MedicineManagement.model.Product;
import MedicineManagement.model.Thuoc;
import MedicineManagement.model.TuThuoc;
import MedicineManagement.save.ReadExcelFileDemo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private GridPane gpCaiDat;

    @FXML
    private GridPane gpTienIch;

    @FXML
    private AnchorPane gpToaThuoc;

    @FXML AnchorPane toaThuocView;

    @FXML
    private GridPane gpTuThuoc;

    @FXML
    private Button btnAddMed;

    @FXML
    private Button btnCaiDat;

    @FXML
    private Button btnTienIch;

    @FXML
    private Button btnToaThuoc;

    @FXML
    private Button btnTuThuoc;

    @FXML
    private TextField tfSearch;
    
    @FXML
    private Label lblStatus;

    @FXML
    private Label lblStatusMini;
    
    @FXML
    private VBox pnlStatus;

    @FXML private ComboBox filterBox;
    
    @FXML
    private ImageView btnClose;
    //insert data on table view

    @FXML
    public TableView<Product> table;
    @FXML
    private TableColumn<Product, Integer> productID;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Thuoc, String> expiredDate;
    @FXML
    private TableColumn<Product, String> effect;
    @FXML
    private TableColumn<Product, String> unit;
    @FXML
    private TableColumn<Product, Integer> quantity;
    @FXML
    private ChoiceBox<String> choiceBox;

    private PresController presController ;
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
            presController= new PresController(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //Click on button
    @FXML
    private void handleClicks(ActionEvent event) throws IOException{
        if(event.getSource() == btnTuThuoc){
            lblStatusMini.setText("/home/TuThuoc");
            lblStatus.setText("TỦ THUỐC CỦA BẠN");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(37,37,39),CornerRadii.EMPTY,Insets.EMPTY)));
            gpTuThuoc.toFront();
        }
        if(event.getSource() == btnToaThuoc){
            lblStatusMini.setText("/home/ToaThuoc");
            lblStatus.setText("KHO LƯU TOA THUỐC");
            gpToaThuoc.toFront();
        }
        if(event.getSource() == btnTienIch){
            lblStatusMini.setText("/home/TienIch");
            lblStatus.setText("TIN MỚI TRONG NGÀY");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(37,37,39),CornerRadii.EMPTY,Insets.EMPTY)));
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
                ReadExcelFileDemo excel = new ReadExcelFileDemo();
                System.out.println(table.getItems());
                excel.setExcelList(new ArrayList<>(table.getItems()));
//                System.out.println(excel.getDiffNumRow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.exit(0);
        }
    }
    //Add Medicine data from button , Save data or clear data

    @FXML
    public void getAddView(MouseEvent event) {
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
        productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        expiredDate.setCellValueFactory(cellData ->{
                    if(cellData.getValue() instanceof Thuoc){
                        return new SimpleStringProperty(DateFormat.getDateInstance().format(cellData.getValue().getExpiredDate()));
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
                    if(product instanceof Thuoc){
                        return true;
                    }
                }
                else if(newVal == "Dụng Cụ"){
                    if(product instanceof  DungCu){
                        return true;
                    }
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

                if(product.getName().toLowerCase().indexOf(lowerCase) != -1){
                    return true;
                }else if(product instanceof Thuoc && filterBox.getValue() == "Thuốc"){
                    if(((Thuoc) product).getEffect().toLowerCase().indexOf(lowerCase) != -1 ){
                        return true;
                    } else {
                        return false;
                    }
                } else if(product instanceof DungCu &&  filterBox.getValue() == "Dụng Cụ"){
                    if(((DungCu) product).getUse().toLowerCase().indexOf(lowerCase) != -1 ){
                        return true;
                    } else {
                        return false;
                    }
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


    private void addButtonToTable() throws IOException {
        TableColumn<Product, Void> colBtn = new TableColumn("");
        colBtn.setMaxWidth(1200);
        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
            @Override
            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
                final TableCell<Product, Void> cell = new TableCell<Product, Void>() {

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
                                addMedController.setTextField(product.getProductID(), product.getName(),
                                        product.getQuantity(), ((Thuoc)product).getLink(), product.getUnit(), ((Thuoc) product).getExpiredDate(), ((Thuoc) product).getEffect());
                                addMedController.showStage();
                            } else if (product instanceof DungCu) {
                                AddDCController addDCController = new AddDCController(Controller.this, product);
                                addDCController.setTextField1(product.getProductID(), product.getName(), product.getQuantity(), ((Thuoc)product).getLink(), product.getUnit(), ((DungCu) product).getUse());
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
                            if (product instanceof Thuoc) {
                                ShowDetailController showDetailController = new ShowDetailController(Controller.this, product);
                                showDetailController.setTextField(product.getInfo());
                                showDetailController.showStage();
                            }
                            // else if(product instanceof DungCu){
                            //     AddDCController addDCController = new AddDCController(Controller.this , product);
                            //     addDCController.setTextField1(product.getProductID(),product.getName(),product.getQuantity(),product.getLink(),product.getUnit(),((DungCu)product).getUse());
                            //     addDCController.showStage();
                            //     }
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
    public void setToaThuocView(AnchorPane x) {
        this.toaThuocView.getChildren().setAll(x);
    }
}
