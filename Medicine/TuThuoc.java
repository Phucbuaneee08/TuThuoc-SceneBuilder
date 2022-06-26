package Medicine;

import Medicine.controller.ProductController;

import java.util.ArrayList;
import java.util.Date;

public class TuThuoc {
    Date date = new Date();

    Thuoc med1 = new Thuoc(1,"thuoc1",1,"abc","1",date,"none");
    Thuoc med2 = new Thuoc(2,"thuoc2",5,"abc","1",date,"none");
    DungCu dungcu1 = new DungCu(3,"Dau Cao", 3,"abc","123","Xoa bop");
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

    public void testadd(){
        listProduct.add(med1);
        listProduct.add(dungcu1);
        listProduct.add(med2);
    }
}
