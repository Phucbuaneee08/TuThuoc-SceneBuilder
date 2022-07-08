package Prj2.controller;

import Prj2.Service.CrawlInfo;
import Prj2.model.TinTuc;
import Prj2.model.TinTucBox;
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
import java.lang.ModuleLayer.Controller;

public class TinTucController {
    private Controller controller ;
    ObservableList<TinTuc> list = FXCollections.observableArrayList();
    @FXML private ListView<TinTuc> lvTinTuc ;
    @FXML private Button btnPre;
    private int page = 1;
    public TinTucController(Prj2.controller.Controller controller2) throws IOException {
        this.controller = controller;
        FXMLLoader pane = new FXMLLoader(getClass().getResource("/Prj2/View/TienIch.fxml"));
        pane.setController(this);
        controller2.setTienIchView(pane.load());
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
        list.addAll(data.crawlTinTuc(page));
        lvTinTuc.getItems().addAll(list);
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