package MedicineManagement.controller;

import MedicineManagement.model.DungCu;
import MedicineManagement.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddDCController implements Initializable{

    private final Stage stage ;
    private final Controller controller;
    public AddDCController(Controller controller){
        this.controller = controller;
        stage = new Stage();
        // TO
        try {
            FXMLLoader parent =new FXMLLoader((getClass().getResource("/MedicineManagement/View/AddDC.fxml")));
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
            FXMLLoader parent =new FXMLLoader((getClass().getResource("/MedicineManagement/View/AddDC.fxml")));
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
    private Button btnSave;

    @FXML
    private TextField tfEffect;

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
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    public void actionSave() {
        int rs = controller.main.getRsDC();
        DungCu DungCu = new DungCu(rs,tfName.getText(),tfUnit.getText(),Integer.parseInt(tfQuantity.getText()),tfEffect.getText());
        controller.main.getList().add(DungCu);
        controller.main.setRsDC(rs+1);
        stage.close();
    }
    public void actionSave(Product x){
        x.setName(tfName.getText());
        ((DungCu)x).setQuantity(Integer.parseInt(tfQuantity.getText()));
        x.setUnit(tfUnit.getText());
        ((DungCu)x).setUse(tfEffect.getText());
        controller.table.refresh();
        stage.close();
    }

    void setTextField(String name,int quantity,String unit,String use){
        tfName.setText(name);
        tfQuantity.setText(quantity+"");
        tfUnit.setText(unit);
        tfEffect.setText(use);
    }

}
