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
import Prj2.model.TuThuoc;
import Prj2.save.ReadExcelFileDemo;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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
    private GridPane gpCaiDat;

    @FXML
    private GridPane gpTienIch;

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

    // public ObservableList<Product> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url , ResourceBundle rb)  {
        showTuThuoc();
        //
       
        // Set and add value for choiceBox
        choiceBox.getItems().add("Thuốc");
        choiceBox.getItems().add("Dụng Cụ");
        choiceBox.setValue("Thuốc");
        try {
            addButtonToTable();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/Prj2/View/ToaThuoc.fxml"));
            gpToaThuoc.getChildren().removeAll();
            gpToaThuoc.getChildren().setAll(pane);
            gpToaThuoc.toFront();
        }
        if(event.getSource() == btnAddTT){
            Parent parent = FXMLLoader.load(getClass().getResource("/Prj2/View/AddToaThuoc.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(parent)); 
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
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
                    m1.setOnAction(event->{
                        Product product = table.getSelectionModel().getSelectedItem();
                        if(product instanceof Thuoc){
                            AddMedController addMedController = new AddMedController(Controller.this ,product) ;
                            addMedController.setTextField(product.getProductID(), product.getName(), 
                            product.getQuantity(), product.getLink(),product.getUnit(), ((Thuoc) product).getExpiredDate(),((Thuoc)product).getEffect());
                            addMedController.showStage();}
                        else if(product instanceof DungCu){
                            AddDCController addDCController = new AddDCController(Controller.this , product);
                            addDCController.setTextField1(product.getProductID(),product.getName(),product.getQuantity(),product.getLink(),product.getUnit(),((DungCu)product).getUse());
                            addDCController.showStage();
                            }
                    });
                    //set action for remove button
                    m2.setOnAction(event->{
                            Product SingleProduct = table.getSelectionModel().getSelectedItem();
                            main.getList().remove(SingleProduct);
                    });
                    m3.setOnAction(event->{
                        Product product = table.getSelectionModel().getSelectedItem();
                        if(product instanceof Thuoc){
                            ShowDetailController showDetailController = new ShowDetailController(Controller.this ,product) ;
                            showDetailController.setTextField(product.getName());
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
        


        //Search bar 
        FilteredList<Product> filteredData = new FilteredList<>(main.getList(),b->true);
        this.tfSearch.textProperty().addListener(((observableValue, oldVal, newVal) ->{
            filteredData.setPredicate(product -> {
                if(newVal == null || newVal.isEmpty()){
                    return true;
                }

                String lowerCase =newVal.toLowerCase();

                if(product.getName().toLowerCase().indexOf(lowerCase) != -1){
                    return true;
                }else if(product instanceof Thuoc){
                    if(((Thuoc) product).getEffect().toLowerCase().indexOf(lowerCase) != -1){
                        return true;
                    } else {
                        return false;
                    }
                } else if(product instanceof DungCu){
                    if(((DungCu) product).getUse().toLowerCase().indexOf(lowerCase) != -1){
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

        //Option
        
    }
    
}
