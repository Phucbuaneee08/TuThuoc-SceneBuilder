package MedicineManagement.controller;

import MedicineManagement.Service.CrawlInfo;
import MedicineManagement.model.TinTuc;
import MedicineManagement.model.TinTucBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;

public class TinTucController {
    @FXML private ListView<TinTuc> lvTinTuc ;
    @FXML private Button btnPre;
    private int page = 1;
    public TinTucController(Controller controller) throws IOException {
        FXMLLoader pane = new FXMLLoader(getClass().getResource("/MedicineManagement/View/TienIch.fxml"));
        pane.setController(this);
        controller.setTienIchView(pane.load());
        this.btnPre.setOnAction(event -> {
            try {
                addTinTuc();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        this.getDataView();
    }
    CrawlInfo data = new CrawlInfo();
    public void getDataView() {
        lvTinTuc.getItems().addAll(data.crawlTinTuc(page));
        lvTinTuc.setCellFactory(listView -> new TinTucBox());
    }
    public void addTinTuc() throws IOException{
        this.page++;
        lvTinTuc.getItems().addAll(data.crawlTinTuc(this.page));
    }
}
