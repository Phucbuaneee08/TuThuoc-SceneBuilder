package Prj2.controller;

import Prj2.inteface.ShowAble;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public abstract class ShowableAbstractClass implements ShowAble {

    public void showStage(Stage stage){
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    public void loadStage(String location, Stage stage){
        try {
            FXMLLoader parent =new FXMLLoader((getClass().getResource(location)));
            parent.setController(this);
            stage.setScene(new Scene(parent.load()));
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}