package Prj2.controller;

import Prj2.inteface.Addable;
import javafx.stage.Stage;

public abstract class AddAbstractClass extends ShowableAbstractClass implements Addable {
    @Override
    public abstract void actionAdd();

    public void showStage(Stage stage){
        super.showStage(stage);
    }

    public void loadStage(String location, Stage stage){
        super.loadStage(location,stage);
    }
}