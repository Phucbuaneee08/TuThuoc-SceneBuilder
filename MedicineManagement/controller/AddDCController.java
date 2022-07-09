package MedicineManagement.controller;

import MedicineManagement.inteface.EditAble;
import MedicineManagement.model.DungCu;
import MedicineManagement.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddDCController extends AddAbstractClass implements EditAble {
    private final Stage stage ;
    private final Controller controller;

    @FXML private Button btnSave;

    @FXML private TextField tfEffect;

    @FXML private TextField tfName;

    @FXML private TextField tfQuantity;

    @FXML private TextField tfUnit;
    public AddDCController(Controller controller){
        this.controller = controller;
        stage = new Stage();
        loadStage();
        btnSave.setOnAction(event -> actionAdd());
    }

    public AddDCController(Controller controller, Product x){
        this.controller = controller;
        stage = new Stage();
        loadStage();
        btnSave.setOnAction(event ->actionEdit(x));
    }

    @Override
    public void loadStage() {
        super.loadStage("/MedicineManagement/View/AddDungCu.fxml", stage);
    }

    @Override
    public void showStage(){
        super.showStage(this.stage);
    }
    @Override
    public void actionAdd() {
        int rs = controller.main.getRsDC();
        DungCu DungCu = new DungCu(rs,tfName.getText(),tfUnit.getText(),Integer.parseInt(tfQuantity.getText()),tfEffect.getText());
        controller.main.getList().add(DungCu);
        controller.main.setRsDC(rs+1);
        stage.close();
    }

    @Override
    public void actionEdit(Product x){
        x.setName(tfName.getText());
        ((DungCu)x).setQuantity(Integer.parseInt(tfQuantity.getText()));
        x.setUnit(tfUnit.getText());
        ((DungCu)x).setUse(tfEffect.getText());
        controller.table.refresh();
        stage.close();
    }
    @Override
    public void setTextField(Product x){
        tfName.setText(x.getName());
        tfQuantity.setText(((DungCu)x).getQuantity()+"");
        tfUnit.setText(x.getUnit());
        tfEffect.setText(((DungCu)x).getUse());
    }
}
