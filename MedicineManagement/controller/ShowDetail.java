package MedicineManagement.controller;

import MedicineManagement.model.Product;
import MedicineManagement.model.Thuoc;
import MedicineManagement.model.TinTuc;
import MedicineManagement.model.TinTucBox;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ShowDetail extends ShowableAbstractClass{
    private final Stage stage ;
    private final Product product;
    @FXML
    private VBox tfDetail;
    @FXML private ListView<TinTuc> listTinTuc = new ListView<>() ;
    public ShowDetail(Product x){
        stage = new Stage();
        this.product = x;
        this.loadStage();
    }
    public void showStage(){
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    @Override
    public void loadStage() {
        super.loadStage("MedicineManagement/View/ShowDetail.fxml",this.stage);
    }
    public void setVBox(ArrayList<Text> x ) throws IOException{
        for(Text i : x){
            i.setWrappingWidth(tfDetail.getPrefWidth() - 30 );
            tfDetail.getChildren().add(i);
        }
        if(this.product instanceof Thuoc){
            ObservableList<TinTuc> listCheck = ((Thuoc)this.product).getNews();
            if(listCheck != null) {
                listTinTuc.getItems().addAll(listCheck);
                listTinTuc.setCellFactory(listView -> new TinTucBox());
                tfDetail.getChildren().add(listTinTuc);
            }
            tfDetail.setSpacing(10);
        }
    }
}
