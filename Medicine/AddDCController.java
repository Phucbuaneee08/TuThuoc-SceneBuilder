package Medicine;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


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
        int rs = controller.main.getRs();
        DungCu dungCu = new DungCu(rs+1,tfName.getText(),11,"ABC",tfUnit.getText(),"ABC");
        controller.main.getList().add(dungCu);
        controller.main.setRs(rs+1);
        stage.close();
    }
}