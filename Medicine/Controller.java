package Medicine;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Controller implements Initializable {
    @FXML
    private GridPane gpCaiDat;

    @FXML
    private GridPane gpTienIch;

    @FXML
    private GridPane gpToaThuoc;

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
    public TuThuoc main = new TuThuoc();
//    public int rs = 0;
    
//    public ObservableList<Product> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url , ResourceBundle rb)  {

        showTuThuoc();
        choiceBox.getItems().add("Thuốc");
        choiceBox.getItems().add("Dụng Cụ");
    }
    //Click on button
    @FXML
    private void handleClicks(ActionEvent event){
        if(event.getSource() == btnTuThuoc){
            lblStatusMini.setText("/home/TuThuoc");
            lblStatus.setText("TỦ THUỐC CỦA BẠN");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(37,37,39),CornerRadii.EMPTY,Insets.EMPTY)));
            gpTuThuoc.toFront();
        }
        if(event.getSource() == btnToaThuoc){
            lblStatusMini.setText("/home/ToaThuoc");
            lblStatus.setText("KHO LƯU TOA THUỐC");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(37,37,39),CornerRadii.EMPTY,Insets.EMPTY)));
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
        if(event.getSource() == btnAddMed){
            
        }
    
    //Close app
    }
    @FXML
    private void handleClose(javafx.scene.input.MouseEvent event){
        if(event.getSource()==btnClose){
            try {
                ReadExcelFileDemo excel = new ReadExcelFileDemo();
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
    public void getAddView(MouseEvent event) throws Exception{
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
        table.setItems(main.getList());
    }
}
