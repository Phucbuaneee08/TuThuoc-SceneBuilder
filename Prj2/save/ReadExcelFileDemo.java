package Prj2.save;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import Prj2.model.DungCu;
import Prj2.model.Product;
import Prj2.model.Thuoc;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFileDemo
{
    private final String excelPath = "C:\\DevC++\\ViscodeCC++\\.vscode\\FirstJavaFxProject\\FirstJavaFxProject\\src\\Prj2\\save\\QLTuThuoc.xlsx";
    public  ArrayList<Product> getExcelFileDemo() throws IOException,Exception
    {
        ArrayList<Product> list = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        InputStream inputStream =  new FileInputStream(new File(excelPath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Sheet secondSheet = workbook.getSheetAt(4);
        Iterator<Row> iteratorThuoc = firstSheet.iterator();
        Iterator<Row> iteratorDC = secondSheet.iterator();
        iteratorThuoc.next();
        iteratorDC.next();
        while (iteratorThuoc.hasNext()) {
            Row nextRow = iteratorThuoc.next();
            if(!isRowEmpty(nextRow)){
                int productID =(int)nextRow.getCell(0).getNumericCellValue();
                String name = nextRow.getCell(1).getStringCellValue();
                int quantity = (int)nextRow.getCell(2).getNumericCellValue();
                String unit = nextRow.getCell(3).getStringCellValue();
                String effect = nextRow.getCell(4).getStringCellValue();
                Date expiredDate = nextRow.getCell(5).getDateCellValue() ;
                list.add(new Thuoc(productID,name,quantity,unit,expiredDate,effect));
            }
        }
        while (iteratorDC.hasNext()) {
            Row nextRow = iteratorDC.next();
            if(!isRowEmpty(nextRow)){
                int productID =(int)nextRow.getCell(0).getNumericCellValue();
                String name = nextRow.getCell(1).getStringCellValue();
                int quantity = (int)nextRow.getCell(2).getNumericCellValue();
                String unit = nextRow.getCell(3).getStringCellValue();
                String effect = nextRow.getCell(4).getStringCellValue();
                list.add(new DungCu(productID,name,quantity,unit,effect));
            }
        }
        workbook.close();
        inputStream.close();
        return list;
    }

    public boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
                return false;
        }
        return true;
    }

    public void setExcelList(ArrayList<Product> saveList) throws IOException{
        InputStream inputStream =  new FileInputStream(new File(excelPath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Sheet secondSheet = workbook.getSheetAt(4);
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/mm/yyyy"));
        int indexThuoc = 1;
        int indexDC = 1;
        for (int index = firstSheet.getLastRowNum(); index > firstSheet.getFirstRowNum(); index--) {
            if(firstSheet.getRow(index) != null){
            firstSheet.removeRow( firstSheet.getRow(index));}
        }
        for (int index = secondSheet.getLastRowNum(); index > secondSheet.getFirstRowNum(); index--) {
            if(secondSheet.getRow(index) != null){
                secondSheet.removeRow( secondSheet.getRow(index));}
        }
        //rewrite
        for(Product x: saveList){
            if(x instanceof Thuoc ){
                firstSheet.createRow(indexThuoc);
                Row addRowThuoc = firstSheet.getRow(indexThuoc);
                Cell cell;
                cell = addRowThuoc.createCell(0);
                cell.setCellValue(x.getProductID());
                cell =addRowThuoc.createCell(1);
                cell.setCellValue(x.getName());
                cell = addRowThuoc.createCell(2);
                cell.setCellValue(x.getQuantity());
                cell =addRowThuoc.createCell(3);
                cell.setCellValue(x.getUnit());
                cell =addRowThuoc.createCell(4);
                String effect = ((Thuoc) x).getEffect();
                cell.setCellValue(effect);
                cell =addRowThuoc.createCell(5);
                cell.setCellValue(((Thuoc) x).getExpiredDate());
                cell.setCellStyle(cellStyle);
                cell = addRowThuoc.createCell(7);
                cell.setCellValue(((Thuoc)x).getLink());
                indexThuoc++;
            } else if(x instanceof DungCu){
                secondSheet.createRow(indexDC);
                Row addRowDC = secondSheet.getRow(indexDC);
                Cell cell;
                cell = addRowDC.createCell(0);
                cell.setCellValue(x.getProductID());
                cell =addRowDC.createCell(1);
                cell.setCellValue(x.getName());
                cell = addRowDC.createCell(2);
                cell.setCellValue(x.getQuantity());
                cell =addRowDC.createCell(3);
                cell.setCellValue(x.getUnit());
                cell =addRowDC.createCell(4);
                String effect = ((DungCu) x).getUse();
                cell.setCellValue(effect);
                indexDC++;
            }
        }
        FileOutputStream fileOut = new FileOutputStream(excelPath);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
        inputStream.close();
    }
    public int getDiffNumRow() throws IOException{
        ArrayList<Product> list = new ArrayList<>();
        InputStream inputStream =  new FileInputStream(new File(excelPath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        int rs = firstSheet.getLastRowNum();
        workbook.close();
        inputStream.close();
        return rs;
    }
}