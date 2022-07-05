package MedicineManagement.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MedicineManagement.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ShowDetailController implements Initializable{
    private  Stage stage ;
    private Controller controller;
    
    @FXML
    private Text tfDetail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    } 
    public ShowDetailController(Controller controller, Product x){
        this.controller = controller;
        stage = new Stage();
        // TO
        try {
            FXMLLoader parent =new FXMLLoader((getClass().getResource("/MedicineManagement/View/ShowDetail.fxml")));
            parent.setController(this);
            stage.setScene(new Scene(parent.load()));
            stage.initStyle(StageStyle.UTILITY);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void showStage(){
        stage.show();
    }
    void setTextField(String name){
        
       tfDetail.setText(name);

    }
}
