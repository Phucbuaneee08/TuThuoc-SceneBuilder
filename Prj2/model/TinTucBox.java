package Prj2.model;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TinTucBox extends ListCell<TinTuc> {
    private ImageView imageView;
    private HBox hbox;
    private Text name;
    private Text des;

    public TinTucBox() {
        super();
        this.name = new Text();
        this.des = new Text();
        this.imageView =new ImageView();
        VBox vBox = new VBox(this.name, this.des);
        hbox = new HBox(imageView,vBox);
    }

    @Override
    protected void updateItem(TinTuc item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) { // <== test for null item and empty parameter
            imageView.setImage(new Image(item.getUrl()));
            imageView.setFitWidth(260);
            imageView.setFitHeight(173);
            name.setText(item.getName());
            name.setWrappingWidth(350);
            name.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR,20));
            des.setText(item.getDes());
            des.setWrappingWidth(350);
            hbox.setPadding(new Insets(5,5,5,5));
            hbox.setSpacing(10);
            setGraphic(hbox);
        } else {
            setGraphic(null);
        }
    }
}