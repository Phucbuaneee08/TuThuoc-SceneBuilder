package Prj2;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AddMedController implements Initializable{

    private  Stage stage ;
    private Controller controller ;
    public AddMedController(Controller controller){

            this.controller = controller;
            stage = new Stage();
            // TO
            try {
                FXMLLoader parent =new FXMLLoader((getClass().getResource("AddMed.fxml")));
                parent.setController(this);
                stage.setScene(new Scene(parent.load())); 
                stage.initStyle(StageStyle.UTILITY);
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
            FXMLLoader parent =new FXMLLoader((getClass().getResource("AddMed.fxml")));
            parent.setController(this);
            stage.setScene(new Scene(parent.load()));
            stage.initStyle(StageStyle.UTILITY);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        btnSave.setOnAction(event ->actionSave(x));
    }
    @FXML
    private Button btnReset;

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
        stage.show();
    }

        
    public void actionSave() {    
    ZoneId defaultZoneId = ZoneId.systemDefault();
    LocalDate localDate = tfHSD.getValue();
    int rs = controller.main.getRsThuoc();
    Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        Thuoc Thuoc = new Thuoc(rs+1,tfName.getText(),1,"ABC",tfUnit.getText(),date,tfEffect.getText());
        controller.main.getList().add(Thuoc);
        controller.main.setRsThuoc(rs+1);
        stage.close();
    }

    public void actionSave(Product x){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = tfHSD.getValue();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        x.setName(tfName.getText());
        x.setQuantity(Integer.valueOf(tfQuantity.getText()));
        x.setUnit(tfUnit.getText());
        ((Thuoc)x).setExpiredDate(date);
        ((Thuoc)x).setEffect(tfEffect.getText());
        controller.main.getList().set(x.getProductID()-1, x);
        stage.close();
    }

    @FXML
    private void handleClose(javafx.scene.input.MouseEvent event){
        if(event.getSource()==btnClose){
            stage.close();
        }
    }

    void setTextField(int ProductID, String name,int quantity,String link,String unit,Date expireDate,String effect){
        
        tfName.setText(name);
        tfQuantity.setText(quantity+"");
        tfUnit.setText(unit);
        LocalDate localDate = Instant.ofEpochMilli(expireDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        tfHSD.setValue(localDate);
        tfEffect.setText(effect);

    }
   
}
