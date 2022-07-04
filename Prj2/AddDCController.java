package Prj2;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AddDCController implements Initializable{

    private  Stage stage ;
    private Controller controller ;
    public AddDCController(Controller controller){
        this.controller = controller;
        stage = new Stage();
        // TO
        try {
            FXMLLoader parent =new FXMLLoader((getClass().getResource("AddDungCu.fxml")));
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
            FXMLLoader parent =new FXMLLoader((getClass().getResource("AddDungCu.fxml")));
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
        stage.show();
    }
    public void actionSave() {
        DungCu DungCu = new DungCu(11,tfName.getText(),11,"ABC",tfUnit.getText(),"ABC");
        controller.list.add(DungCu);
    }
    public void actionSave(Product x){
    
        x.setName(tfName.getText());
        x.setQuantity(Integer.valueOf(tfQuantity.getText()));
        x.setUnit(tfUnit.getText());
        ((DungCu)x).setUse(tfEffect.getText());
        controller.list.set(x.getProductID()-1, x);
        System.out.println(x.getName());
    }

    void setTextField1(int ProductID, String name,int quantity,String link,String unit,String use){
        
        tfName.setText(name);
        tfQuantity.setText(quantity+"");
        tfUnit.setText(unit);
        tfEffect.setText(use);
       

    }

    @FXML
    private void handleClose(javafx.scene.input.MouseEvent event){
        if(event.getSource()==btnClose){
            stage.close();
        }
    }
}
