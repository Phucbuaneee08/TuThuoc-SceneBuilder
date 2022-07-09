package MedicineManagement.controller;

import MedicineManagement.model.Product;
import MedicineManagement.model.Thuoc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class AddMedController implements Initializable{

    private final Stage stage ;
    private final Controller controller;
    public AddMedController(Controller controller){
        this.controller = controller;
        stage = new Stage();
        // TO
        try {
            FXMLLoader parent =new FXMLLoader((getClass().getResource("/MedicineManagement/View/AddMed.fxml")));
            parent.setController(this);
            stage.setScene(new Scene(parent.load()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public AddMedController(Controller controller, Product x){
        this.controller = controller;
        stage = new Stage();
        // TO
        try {
            FXMLLoader parent =new FXMLLoader((getClass().getResource("/MedicineManagement/View/AddMed.fxml")));
            parent.setController(this);
            stage.setScene(new Scene(parent.load()));
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        btnSave.setOnAction(event ->actionSave(x));
    }
    @FXML
    private Button btnSave;

    @FXML
    private TextField tfEffect;
    @FXML
    private DatePicker tfHSD;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfQuantity;
    @FXML
    private ImageView btnClose;

    @FXML
    private TextField tfUnit;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        btnSave.setOnAction(event ->actionSave());
    }

    public void showStage(){
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

        
    public void actionSave() {
        LocalDate localDate = tfHSD.getValue();
        int rs = controller.main.getRsThuoc();
        Thuoc Thuoc = new Thuoc(rs+1,tfName.getText(),tfUnit.getText(),Integer.parseInt(tfQuantity.getText()),localDate,tfEffect.getText());
        controller.main.getList().add(Thuoc);
        controller.main.setRsThuoc(rs+1);
        stage.close();
    }

    public void actionSave(Product x){
        LocalDate localDate = tfHSD.getValue();
        x.setName(tfName.getText());
        ((Thuoc)x).setQuantity(Integer.parseInt(tfQuantity.getText()));
        x.setUnit(tfUnit.getText());
        ((Thuoc)x).setExpiredDate(localDate);
        ((Thuoc)x).setEffect(tfEffect.getText());
        controller.table.refresh();
        stage.close();
    }

    @FXML
    private void handleClose(javafx.scene.input.MouseEvent event){
        if(event.getSource()==btnClose){
            stage.close();
        }
    }

    void setTextField(String name,int quantity,String unit,LocalDate expireDate,String effect){
        tfName.setText(name);
        tfQuantity.setText(quantity+"");
        tfUnit.setText(unit);
        tfHSD.setValue(expireDate);
        tfEffect.setText(effect);
    }
   
}
