// package Prj2.controller;

// import javafx.beans.property.SimpleStringProperty;

// import java.util.ArrayList;
// import java.util.Date;

// import Prj2.model.DungCu;
// import Prj2.model.Product;
// import Prj2.model.Thuoc;

// public class ProductController {
//     ArrayList<Product> list;

//     public ProductController(ArrayList<Product> list) {
//         this.list = list;
//     }

//     public void addMedicine(int id, String name, int quantỉty, String link, String unit, Date date, String effect){
//         Thuoc MedToAdd = new Thuoc(id,name,quantỉty,link,unit,date,effect);
//         list.add(MedToAdd);
//     }
//     public void removeMedicine(int removeProID ){
//         list.removeIf(x -> x.getProductID() == removeProID);
//     }
//     public void edit (int option, int productID, String value){
//         if (option == 1){
//             editName(productID,value);
//         } else if(option == 3){
//             editLink(productID,value);
//         } else if(option == 4){
//             editUnit(productID,value);
//         } else if(option == 2){
//             editQuantity(productID,Integer.parseInt(value));
//         }
//     }
//     public void edit(int option, int productID , Date date){
//         if(option ==5){
//             editExpiredDate(productID,date);
//         }
//     }
//     public void editName(int productID,String name){
//         findMedicineByID(productID).setName(name);
//     }
//     public void editQuantity(int productID, int quantity){
//         findMedicineByID(productID).setQuantity(quantity);
//     }
//     public void editLink(int productID, String link){
//         findMedicineByID(productID).setLink(link);
//     }
//     public void editUnit(int productID, String unit){
//         findMedicineByID(productID).setUnit(unit);
//     }

//     public void editExpiredDate(int productID, Date date){
//         Thuoc thuoc = (Thuoc)findMedicineByID(productID);
//         thuoc.setExpiredDate(date);
//     }
//     public void editUses(int productID, String uses){
//         DungCu dungCu = (DungCu)findMedicineByID(productID);
//         dungCu.setUse(uses);
//     }

//     public Product findMedicineByID(int productID){
//         for (Product product : list) {
//             if (product.getProductID() == productID) {
//                 return product;
//             }
//         }
//         return null;
//     }

//     public void sortById() {
//         list.sort((x1,x2) -> {
//             int cmp = x1.getProductID()-(x2.getProductID());
//             return cmp;
//         });
//     }
//     public void sortByName() {
//         list.sort((x1,x2) -> {
//             int cmp = x1.getName().compareTo(x2.getName());
//             return cmp;
//         });
//     }
// //    public void sortByExpiredDate() {
// //        ArrayList<Thuoc> listThuoc = new ArrayList<Thuoc>();
// //        ArrayList<DungCu> listDungCu = new ArrayList<DungCu>();
// //        for (Product x : list){
// //            if(x instanceof Thuoc) listThuoc.add((Thuoc)x);
// //            else if(x instanceof DungCu) listDungCu.add((DungCu)x);
// //        }
// //        listThuoc.sort((x1,x2) -> x1.getExpiredDate().compareTo(x2.getExpiredDate()));
// //        list.removeAll(list);
// //        list.addAll(listThuoc);
// //        list.addAll(listDungCu);
// //    }
//     public void search(String name){

//     }
// }