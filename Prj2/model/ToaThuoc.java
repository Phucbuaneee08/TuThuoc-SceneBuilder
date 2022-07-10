package Prj2.model;

import java.util.ArrayList;
import java.util.Date;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToaThuoc {
    private int presID;

    public ToaThuoc() {
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ArrayList<ThuocTrongToa> getListProduct() {
        return this.listProduct;
    }

    public ToaThuoc(int presID, String name, Date startedDate, Date endDate, ArrayList<ThuocTrongToa> listProduct) {
        this.presID = presID;
        this.name = name;
        this.startedDate = startedDate;
        this.endDate = endDate;
        this.listProduct = listProduct;
    }

    private String name;
    private Date startedDate;
    private Date endDate;
    private ArrayList<ThuocTrongToa> listProduct = new ArrayList<>();

    public int getPresID() {
        return presID;
    }

    public void setPresID(int presID) {
        this.presID = presID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Text> showInfo(){
        ArrayList<Text> info = new ArrayList<>();
        info.add(new Text("Tên toa thuốc: " + getName()));
        info.add(new Text("Ngày bắt đầu: " + getStartedDate()));
        info.add(new Text ("Ngày kết thúc: " + getEndDate()));
        info.add(new Text("Chi tiết: "));
        for(ThuocTrongToa x : this.listProduct){
            info.add(new Text(x.getName() + "  " + x.getLieu()));
        }
        return info;
    }
    public int status(){
        Date today = new Date();
        if(startedDate.compareTo(today) > 0){
            return -1; // chua den thoi gian uong
        } else if(startedDate.compareTo(today) <= 0 && endDate.compareTo(today) >= 0 ){
            return 0; // da uong thuoc
        } else {
            return 1; // da uong xong
        }
    }
   
}