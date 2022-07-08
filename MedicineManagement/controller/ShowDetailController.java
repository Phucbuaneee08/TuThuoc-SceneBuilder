package MedicineManagement.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import MedicineManagement.model.Product;
import MedicineManagement.model.Thuoc;
import MedicineManagement.model.TinTuc;
import MedicineManagement.model.TinTucBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class ShowDetailController implements Initializable{
    private  Stage stage ;
    private Controller controller;
    private Product product;
    @FXML
    private VBox tfDetail;
    @FXML private ListView<TinTuc> listTinTuc = new ListView<>() ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    } 
    public ShowDetailController(Controller controller, Product x){
        this.controller = controller;
        stage = new Stage();
        this.product = x;
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
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    public void setTextField(ArrayList<Text> x ) throws IOException{
        for(Text i : x){
            i.setWrappingWidth(tfDetail.getPrefWidth() - 30 );
            tfDetail.getChildren().add(i);
        }
        ObservableList<TinTuc> listCheck = ((Thuoc)this.product).getNews();
        if(listCheck != null) {
            listTinTuc.getItems().addAll(listCheck);
            listTinTuc.setCellFactory(new Callback<ListView<TinTuc>, ListCell<TinTuc>>() {
                @Override
                public ListCell<TinTuc> call(ListView<TinTuc> listView) {
                    return new TinTucBox();
                }
            });
            tfDetail.getChildren().add(listTinTuc);
        }
        tfDetail.setSpacing(10);
    }
}
