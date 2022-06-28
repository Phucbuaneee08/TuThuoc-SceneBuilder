package Prj2;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class AddMedController implements Initializable{

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public Date x = new Date();
    public void actionSave(ActionEvent event, TableView table) throws Exception{

        Product Thuoc = new Thuoc(11,tfName.getText(),11,"ABC",tfUnit.getText(),x,tfEffect.getText());
        // list.add(Thuoc);
        table.getItems().add(Thuoc);




    }
    
   
    
    
}
