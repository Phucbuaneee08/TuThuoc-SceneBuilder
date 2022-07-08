package MedicineManagement.controller;

import MedicineManagement.Service.CrawlInfo;
import MedicineManagement.model.TinTuc;
import MedicineManagement.model.TinTucBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;



import java.io.IOException;

public class TinTucController {
    private Controller controller ;
    @FXML private ListView<TinTuc> lvTinTuc ;
    @FXML private Button btnPre;
    private int page = 1;
    public TinTucController(Controller controller) throws IOException {
        this.controller = controller;
        FXMLLoader pane = new FXMLLoader(getClass().getResource("/MedicineManagement/View/TienIch.fxml"));
        pane.setController(this);
        controller.setTienIchView(pane.load());
        this.btnPre.setOnAction(event -> {
            try {
                addTinTuc(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        this.getDataView();
    }
    CrawlInfo data = new CrawlInfo();
    public void getDataView() throws  IOException {
        lvTinTuc.getItems().addAll(data.crawlTinTuc(page));
        lvTinTuc.setCellFactory(new Callback<ListView<TinTuc>, ListCell<TinTuc>>() {
            @Override
            public ListCell<TinTuc> call(ListView<TinTuc> listView) {
                return new TinTucBox();
            }
        });
    }

    public void addTinTuc (ActionEvent event) throws IOException{
        this.page++;
        lvTinTuc.getItems().addAll(data.crawlTinTuc(this.page));
    }
}
