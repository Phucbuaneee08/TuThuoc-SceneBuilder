package Prj2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Prj2.model.ToaThuoc;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ShowDetailTTController implements Initializable {
    private  Stage stage ;
    private ToaThuocViewController controller;
    
    @FXML
    private VBox vbDetail;
    private ToaThuoc x ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    } 
    public ShowDetailTTController(ToaThuocViewController presController, ToaThuoc x){
        this.controller = presController;
        this.x = x;
        stage = new Stage();
        // TO
        try {
            FXMLLoader parent =new FXMLLoader((getClass().getResource("/Prj2/View/detailToaThuoc.fxml")));
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

    void setTextField(ArrayList<Text> arrayList){
        System.out.println("x");
        for(Text i : arrayList){
            i.setWrappingWidth(vbDetail.getPrefWidth() - 30);
            vbDetail.getChildren().add(i);
        }
        vbDetail.setSpacing(5);
    }
    
}
