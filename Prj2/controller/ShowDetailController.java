package Prj2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Prj2.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ShowDetailController implements Initializable{
    private  Stage stage ;
    private Controller controller;
    
    @FXML
    private VBox tfDetail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    } 
    public ShowDetailController(Controller controller, Product x){
        this.controller = controller;
        stage = new Stage();

        // TO
        try {
            FXMLLoader parent =new FXMLLoader((getClass().getResource("/Prj2/View/ShowDetail.fxml")));
            parent.setController(this);
            stage.setScene(new Scene(parent.load()));
            stage.initStyle(StageStyle.UTILITY);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void showStage(){
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    void setTextField(ArrayList<Text> x){
        for(Text i : x){
            i.setWrappingWidth(tfDetail.getPrefWidth() - 30 );
            tfDetail.getChildren().add(i);
        }
    }
}