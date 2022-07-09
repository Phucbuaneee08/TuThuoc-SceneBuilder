package MedicineManagement.controller;

import MedicineManagement.inteface.EditAble;
import MedicineManagement.model.Product;
import MedicineManagement.model.Thuoc;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddMedController extends AddAbstractClass implements EditAble {
    private final Stage stage ;
    private final Controller controller;
    @FXML
    private Button btnSave;

    @FXML
    private TextField tfEffect;
    @FXML
    private DatePicker tfHSD;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfQuantity;
    @FXML
    private TextField tfUnit;
    public AddMedController(Controller controller){
        this.controller = controller;
        stage = new Stage();
        loadStage("MedicineManagement/View/AddMed.fxml",stage);
        btnSave.setOnAction(event ->actionAdd());
    }

    public AddMedController(Controller controller, Product x){
        this.controller = controller;
        stage = new Stage();
        // TO
        loadStage("MedicineManagement/View/AddMed.fxml",stage);
        btnSave.setOnAction(event ->actionEdit(x));
    }
    @Override
    public void showStage(){
        super.showStage(this.stage);
    }
    @Override
    public void loadStage() {
        super.loadStage("MedicineManagement/View/AddMed.fxml",stage);
    }
    @Override
    public void actionAdd() {
        int rs = controller.main.getRsThuoc();
        Thuoc thuoc = new Thuoc(rs+1,tfName.getText(),tfUnit.getText(),Integer.parseInt(tfQuantity.getText()),tfHSD.getValue(),tfEffect.getText());
        controller.main.getList().add(thuoc);
        controller.main.setRsThuoc(rs+1);
        stage.close();
    }
    @Override
    public void actionEdit(Product x){
        x.setName(tfName.getText());
        x.setUnit(tfUnit.getText());
        ((Thuoc)x).setQuantity(Integer.parseInt(tfQuantity.getText()));
        ((Thuoc)x).setExpiredDate(tfHSD.getValue());
        ((Thuoc)x).setEffect(tfEffect.getText());
        controller.table.refresh();
        stage.close();
    }
    public void setTextField(Product x){
        tfName.setText(x.getName());
        tfQuantity.setText(((Thuoc)x).getQuantity()+"");
        tfUnit.setText(x.getUnit());
        tfHSD.setValue(((Thuoc)x).getExpiredDate());
        tfEffect.setText(((Thuoc)x).getEffect());
    }
}
