package Prj2.controller;

import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;

import Prj2.model.DungCu;
import Prj2.model.Product;
import Prj2.model.Thuoc;
import Prj2.model.ToaThuoc;
import Prj2.model.TuThuoc;
import Prj2.save.SaveToExcel;
import Prj2.save.SaveToExcel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class Controller implements Initializable {
    @FXML 
    private AnchorPane tienIchView;
    @FXML private AnchorPane gpTienIch;

    @FXML
    private GridPane gpCaiDat;


    @FXML 
    private AnchorPane toaThuocView;
    
    @FXML
    private AnchorPane gpToaThuoc;

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

    @FXML private ComboBox<String> filterBox;
    
    @FXML
    private ImageView btnClose;
    
    @FXML
    private Button btnAddTT;

    @FXML
    private ChoiceBox<String> cbOptions;
 

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
    
    // private Date date = new Date();
    // public static int c = 11;
    private ToaThuocViewController toaThuocViewController ;
    private TinTucController tinTucController;
   
    // public ObservableList<Product> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url , ResourceBundle rb)  {
        showTuThuoc();
        //
       
        // Set and add value for choiceBox
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
            tinTucController = new TinTucController(this);
            toaThuocViewController= new ToaThuocViewController(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Search bar for TableView
        }
    //Click on button
    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if(event.getSource() == btnTuThuoc){
            lblStatusMini.setText("/home/TuThuoc");
            lblStatus.setText("TỦ THUỐC CỦA BẠN");
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
           
            gpTienIch.toFront();
        }
        if(event.getSource() == btnCaiDat){
            lblStatusMini.setText("/home/CaiDat");
            lblStatus.setText("SETTINGS");
            
            gpCaiDat.toFront();
        }
        if(event.getSource() == btnAddMed){
            
        }
    
    //Close app
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
    private TextField tfEffect;

    @FXML
    private DatePicker tfHSD;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfQuantity;

    @FXML
    private TextField tfUnit;
    @FXML
    private ChoiceBox<String> choiceBox;
    public TuThuoc main = new TuThuoc();

   
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
    @FXML void getToaThuocView(MouseEvent event){
        
    }
    //Connect with excel
    //add button cell 
    private void addButtonToTable() throws IOException{
        TableColumn<Product, Void> colBtn = new TableColumn("");
        colBtn.setMaxWidth(800);
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
                    m1.setOnAction(event->{
                        Product product = getTableRow().getItem();
                        if(product instanceof Thuoc){
                            AddMedController addMedController = new AddMedController(Controller.this ,product) ;
                            addMedController.setTextField( product.getName(), 
                           ((Thuoc)product).getQuantity(),product.getUnit(), ((Thuoc) product).getExpiredDate(),((Thuoc)product).getEffect());
                            addMedController.showStage();}
                        else if(product instanceof DungCu){
                            AddDCController addDCController = new AddDCController(Controller.this , product);
                            addDCController.setTextField1(product.getName(),((DungCu)product).getQuantity(),product.getUnit(),((DungCu)product).getUse());
                            addDCController.showStage();
                            }
                    });
                    //set action for remove button
                    m2.setOnAction(event->{
                            Product SingleProduct = getTableRow().getItem();
                            main.getList().remove(SingleProduct);
                            System.out.println(table.getItems());
                    });
                    m3.setOnAction(event->{
                        Product product = getTableRow().getItem();
                        if(product instanceof Thuoc){
                            ShowDetailController showDetailController = new ShowDetailController(Controller.this ,product) ;
                            showDetailController.setTextField(product.getInfo());
                            showDetailController.showStage();}
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

        //Option
        
    }
    public void setToaThuocView(AnchorPane x) {
        this.toaThuocView.getChildren().setAll(x);
    }
    public void setTienIchView(AnchorPane x) {
        this.tienIchView.getChildren().setAll(x);
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

}
