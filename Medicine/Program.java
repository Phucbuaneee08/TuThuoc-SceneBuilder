package Medicine;

import java.util.Date;
import java.util.Scanner;

public class Program {
    static Date date = new Date();
    static TuThuoc tuthuoc = new TuThuoc();

    public static void showMenu(){
        System.out.println("Xin Chao");
        System.out.println("Bạn là :");
        askUser();
    }
    public static void askUser(){
        System.out.println("1. User ");
        System.out.println("2. Manager");
        Scanner askUser = new Scanner(System.in);
        int askUserResult =  askUser.nextInt();
        switch(askUserResult) {
            case 1:
                System.out.println("Xin chao User");
                break;
            case 2:
                System.out.println("Xin chao Manager");
                break;
            default:
                break;
        }
    }
    public static void header(){
        System.out.println("----------------------------------------------");
    }
    public static void showTuThuoc(){
        tuthuoc.controller.addMedicine(4,"hung",6,"a","34",date,"none");

//      tuthuoc.controller.removeMedicine(3);
//        tuthuoc.controller.sortByExpiredDate();
        System.out.println("Tong so luong trong tu:" + tuthuoc.total());
        for (Product x : tuthuoc.getListProduct()){
            header();
            x.showInfo();
            header();
            System.out.println(x.checkType(x));
            System.out.println("");
        }
    }
    public static void menu(){
        System.out.println("1.them san pham");
        System.out.println("2.xoa san pham");
        System.out.println("3.sua san pham");
    }
    public static void main(String[] args){
        //showMenu();
//        showTuThuoc();
        Date date = new Date(122,01,10);

        Date currentDate = new Date();

        System.out.println(date.getTime()-currentDate.getTime());

    }
}
