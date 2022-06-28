package Prj2;

import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.util.Date;
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
    protected TableView<Product> table;
    @FXML
    private TableColumn<Product, Integer> productID;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Thuoc, Date> expiredDate;
    @FXML
    private TableColumn<Product, String> effect;
    @FXML
    private TableColumn<Product, String> unit;
    @FXML
    private TableColumn<Product, Integer> quantity;
    private Date date = new Date();
    public static int c = 11;
     ObservableList<Product> list = FXCollections.observableArrayList(
            new Thuoc(1,"Con đĩ ",10,"Đầu","BRR",date,"none"),
            new Thuoc(2,"Con đĩ ",7,"Đầu","BRR",date,"none"),
            new Thuoc(3,"Con đĩ ",6,"Đầu","BRR",date,"none"),
            new Thuoc(4,"Con đĩ ",5,"Đầu","BRR",date,"none"),
            new DungCu(5,"Bong",5,"abc","Cai","none"),
            new DungCu(6,"Bong",5,"abc","Cai","none")
        );

    @Override
    public void initialize(URL url , ResourceBundle rb)  {

        productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        expiredDate.setCellValueFactory(cellData ->{
                if(cellData.getValue() instanceof Thuoc){
                    return cellData.getValue().getExpiredDate();
                } else {
                    return null;
                }
            }
        );
//        effect.setCellValueFactory(new PropertyValueFactory<>("effect"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
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
    private TextField tfEffect;

    @FXML
    private TextField tfHSD;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfQuantity;

    @FXML
    private TextField tfUnit;
    @FXML
    public void getAddView(MouseEvent event) throws Exception{
        
            Parent parent = FXMLLoader.load(getClass().getResource("/Prj2/AddMed.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
    }
    // public AddMedController medcontroller = new AddMedController(this);
    // public void actionSave(MouseEvent event) throws Exception{
    //     medcontroller.actionSave(event);
    // }
    // public Date x = new Date();
    // public void actionSave(ActionEvent event) throws Exception{
    //     Product Thuoc = new Thuoc(11,tfName.getText(),11,"ABC",tfUnit.getText(),x,tfEffect.getText());
    //     list.add(Thuoc);
    //     table.refresh();

    // }
    public AddMedController controller = new AddMedController();
    public void refresh(){
        this.table.refresh();
    }
    
    

}
