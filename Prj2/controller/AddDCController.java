package Prj2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Prj2.model.DungCu;
import Prj2.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AddDCController implements Initializable{

    private  Stage stage ;
    private Controller controller;
    public AddDCController(Controller controller){
        this.controller = controller;
        stage = new Stage();
        // TO
        try {
            FXMLLoader parent =new FXMLLoader((getClass().getResource("/Prj2/View/AddDC.fxml")));
            parent.setController(this);
            stage.setScene(new Scene(parent.load())); 
            stage.initStyle(StageStyle.UTILITY);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }    
    public AddDCController(Controller controller, Product x){
        this.controller = controller;
        stage = new Stage();
        // TO
        try {
            FXMLLoader parent =new FXMLLoader((getClass().getResource("/Prj2/View/AddDC.fxml")));
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
    private TextField tfHSD;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfQuantity;

    @FXML
    private TextField tfUnit;
    @FXML
    private ImageView btnClose;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        btnSave.setOnAction(event ->actionSave());
    }

    public void showStage(){
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    public void actionSave() {
        int rs = controller.main.getRsDC();
        if(tfName.getText().isEmpty() ||tfQuantity.getText().isEmpty()||tfUnit.getText().isEmpty()||tfEffect.getText().isEmpty()){ 
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Hãy điền vào hết chỗ trống");
            alert.showAndWait();
        }
        else{
             DungCu DungCu = new DungCu(rs,tfName.getText(),Integer.parseInt(tfQuantity.getText()),tfUnit.getText(),"ABC");
            controller.main.getList().add(DungCu);
            controller.main.setRsDC(rs+1);
            stage.close();
        }
    }
    public void actionSave(Product x){
    
        x.setName(tfName.getText());
        x.setQuantity(Integer.valueOf(tfQuantity.getText()));
        x.setUnit(tfUnit.getText());
        ((DungCu)x).setUse(tfEffect.getText());
//        int index = controller.main.getList().indexOf(x);
//        controller.main.getList().set(index, x);
        controller.table.refresh();
        stage.close();
    }

    void setTextField1(int ProductID, String name,int quantity,String link,String unit,String use){
        tfName.setText(name);
        tfQuantity.setText(quantity+"");
        tfUnit.setText(unit);
        tfEffect.setText(use);
    }

}