package Medicine;

import Medicine.controller.ProductController;

import java.util.ArrayList;
import java.util.Date;

public class TuThuoc {
    Date date = new Date();
    private final ArrayList<Product> listProduct = new ArrayList<>();
    ProductController controller = new ProductController(listProduct);

    public int total(){
        int count = 0;
        for(Product x: listProduct){
            count+= x.getQuantity();
        }
        return count;
    }


    public ArrayList<Product> getListProduct() {
        return listProduct;
    }

}
