package Prj2;

import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private TableView<Product> table;
    @FXML
    private TableColumn<Product, Integer> id;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, String> hsd;
    @FXML
    private TableColumn<Product, String> effect;
    @FXML
    private TableColumn<Product, String> unit;
    @FXML
    private TableColumn<Product, Integer> quantity;
    
    ObservableList<Product> list = FXCollections.observableArrayList(
        new Product(1,"Con đĩ ","Hưng","Đầu","BRR",7),
        new Product(2,"Con đĩ ","Quang","Đầu","BRR",7),
        new Product(3,"Con đĩ ","Hoa","Đầu","BRR",7),
        new Product(4,"Con đĩ ","Vanh","Đầu","BRR",7)

        );
    @Override
    public void initialize(URL url , ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        hsd.setCellValueFactory(new PropertyValueFactory<Product,String>("hsd"));
        effect.setCellValueFactory(new PropertyValueFactory<Product,String>("effect"));
        unit.setCellValueFactory(new PropertyValueFactory<Product,String>("unit"));
        quantity.setCellValueFactory(new PropertyValueFactory<Product,Integer>("quantity"));
        table.setItems(list);
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
            System.exit(0);
        }
    }
    //Add Medicine data from button , Save data or clear data
    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Text taName;

    @FXML
    private TextField tfEffect;

    @FXML
    private TextField tfHSD;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfQuantity;

    @FXML
    private TextField tfUnit;
    @FXML
    private void getAddView(MouseEvent event) throws Exception{
        
            Parent parent = FXMLLoader.load(getClass().getResource("/Prj2/AddMed.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        
    }
    
    

}
