package Prj2.controller;

import Prj2.model.Product;
import Prj2.model.ThuocTrongToa;
import Prj2.model.ToaThuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ToaThuocViewController implements Initializable {
    @FXML
    private TableView<ToaThuoc> TVToaThuoc;

    @FXML
    private AnchorPane apToaThuoc;

    @FXML
    private TableColumn<ToaThuoc, String> endDate;

    @FXML
    private TableColumn<ToaThuoc, String> name;

    @FXML
    private TableColumn<ToaThuoc, Integer> presID;

    @FXML
    private TableColumn<ToaThuoc, String> startedDate;
    public void showToaThuoc() throws IOException {
        presID.setCellValueFactory(new PropertyValueFactory<>("presID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        startedDate.setCellValueFactory(new PropertyValueFactory<>("startedDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        TVToaThuoc.setItems(observableList1);
        addButtonToTable();
    }
    Date date1 = new Date();
    private ArrayList<ThuocTrongToa> listToa1 = new ArrayList<>();
    ObservableList<ToaThuoc> observableList1 = FXCollections.observableArrayList(
        new ToaThuoc(1,"ABC",date1,date1,listToa1)
        );

    public ObservableList<ToaThuoc> getListToa(){
        return this.observableList1;
    }
    public ToaThuocViewController(Controller controller) throws IOException {
        FXMLLoader pane = new FXMLLoader(getClass().getResource("/Prj2/View/ToaThuoc.fxml"));
        pane.setController(this);
        controller.setToaThuocView(pane.load());
        this.controller = controller;
        showToaThuoc();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnToaThuoc.setOnAction(event -> showAddToaThuoc(event));
    }
    @FXML
    private VBox vbToaThuoc ;
    @FXML private  Button btnToaThuoc;
    private Controller controller;
    public Date date = new Date();
    public ArrayList<Product> listThuoc = new ArrayList<>();

    public ArrayList<ToaThuoc> listToa = new ArrayList<>();
    public void addThuoc(ThuocTrongToa x){
        listThuoc.add(x);
    }

    


    public ObservableList<Product> getListThuocFromTuThuoc(){
        ObservableList<Product> listThuocTrongTu = FXCollections.observableArrayList(controller.main.getList());
        return listThuocTrongTu;
    }
    // lay list tu tu thuoc
    @FXML
    public void showAddToaThuoc(ActionEvent event){
        AddToaThuoc addToaThuoc = new AddToaThuoc(this);
        addToaThuoc.showStage();
    } //show stage add toa


    private void addButtonToTable() throws IOException{
        TableColumn<ToaThuoc, Void> colBtn = new TableColumn<>("");
        colBtn.setMaxWidth(800);
        Callback<TableColumn<ToaThuoc, Void>, TableCell<ToaThuoc, Void>> cellFactory = new Callback<TableColumn<ToaThuoc, Void>, TableCell<ToaThuoc, Void>>() {
            @Override
            public TableCell<ToaThuoc, Void> call(final TableColumn<ToaThuoc, Void> param) {
                final TableCell<ToaThuoc, Void> cell = new TableCell<ToaThuoc, Void>() {

                    private final MenuButton btn = new MenuButton("");
                    {
                        MenuItem m3 = new MenuItem("detail");
                        btn.getItems().add(m3);
                        //set action for edit button
                        m3.setOnAction(event->{
                            ToaThuoc toaThuoc = getTableRow().getItem();
                            ShowDetailTTController showDetailTTController = new ShowDetailTTController(ToaThuocViewController.this ,toaThuoc) ;
                            showDetailTTController.setTextField(toaThuoc.showInfo());
                            showDetailTTController.showStage();
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
        TVToaThuoc.getColumns().add(colBtn);
    }
}