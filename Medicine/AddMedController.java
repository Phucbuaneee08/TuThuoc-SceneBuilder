package Medicine;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AddMedController implements Initializable{

    private  Stage stage ;
    private Controller controller ;
    private int rs;
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
    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private TextField tfEffect;

    @FXML
    private TextField tfHSD = new TextField();

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfQuantity;

    @FXML
    private TextField tfUnit;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        btnSave.setOnAction(event ->actionSave());
    }

    public void showStage(){
        stage.show();
    }
    public Date x = new Date();
    public void actionSave() {
        int rs = controller.main.getRsThuoc();
        Thuoc thuoc = new Thuoc(rs+1,tfName.getText(),Integer.parseInt(tfQuantity.getText()),"ABC",tfUnit.getText(),x,tfEffect.getText());
        controller.main.getList().add(thuoc);
        controller.main.setRsThuoc(rs+1);
        stage.close();
    }
}
