package Prj2.save;

import Prj2.model.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class SaveToExcel
{
    private final String excelPath = "C:\\DevC++\\ViscodeCC++\\.vscode\\FirstJavaFxProject\\FirstJavaFxProject\\src\\Prj2\\save\\QLTuThuoc.xlsx";
    public  ArrayList<Product> getExcelFileDemo() throws Exception
    {
        ArrayList<Product> list = new ArrayList<>();
        new SimpleDateFormat("dd-MM-yyyy");
        InputStream inputStream =  new FileInputStream(excelPath);
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
                LocalDate expiredDate = nextRow.getCell(5).getDateCellValue().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                list.add(new Thuoc(productID,name,unit,quantity,expiredDate,effect));
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
                list.add(new DungCu(productID,name,unit,quantity,effect));
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
        InputStream inputStream =  new FileInputStream(excelPath);
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
                cell.setCellValue(((Thuoc)x).getQuantity());
                cell =addRowThuoc.createCell(3);
                cell.setCellValue(x.getUnit());
                cell =addRowThuoc.createCell(4);
                String effect = ((Thuoc) x).getEffect();
                cell.setCellValue(effect);
                cell =addRowThuoc.createCell(5);

                cell.setCellValue(Date.from(((Thuoc) x).getExpiredDate().atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()));
                cell.setCellStyle(cellStyle);
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
                cell.setCellValue(((DungCu)x).getQuantity());
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
    public ArrayList<ToaThuoc> getToaThuocFromExcel() throws IOException {
        ArrayList<ToaThuoc> list = new ArrayList<>();
        new SimpleDateFormat("dd-MM-yyyy");
        InputStream inputStream =  new FileInputStream(excelPath);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet toaThuocSheet = workbook.getSheetAt(1);
        Sheet thuocSheet = workbook.getSheetAt(3);
        Iterator<Row> iterToa = toaThuocSheet.iterator();
        iterToa.next();
        while (iterToa.hasNext()){
            Row  nextRow = iterToa.next();
            if(!isRowEmpty(nextRow)){
                int presID =(int)nextRow.getCell(0).getNumericCellValue();
                String name = nextRow.getCell(1).getStringCellValue();
                Date endDate = nextRow.getCell(2).getDateCellValue() ;
                Date startDate = nextRow.getCell(3).getDateCellValue() ;
                Iterator<Row> iterThuoc = thuocSheet.iterator();
                ArrayList<ThuocTrongToa> listThuocTrongToa = new ArrayList<>();
                iterThuoc.next();
                while(iterThuoc.hasNext()){
                    Row row = iterThuoc.next();
                    if((int)row.getCell(4).getNumericCellValue() == presID){
                        listThuocTrongToa.add(new ThuocTrongToa(
                                (int)row.getCell(0).getNumericCellValue(),
                                row.getCell(1).getStringCellValue(),
                                row.getCell(2).getStringCellValue(),
                                row.getCell(3).getStringCellValue())
                        );
                    }
                }
                list.add(new ToaThuoc(presID,name,startDate,endDate,listThuocTrongToa));
            }
        }
        return list;
    }
    public void setToaThuoc(ArrayList<ToaThuoc> saveList) throws IOException{
        InputStream inputStream =  new FileInputStream(excelPath);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(1);
        Sheet secondSheet = workbook.getSheetAt(3);
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/mm/yyyy"));
        int indexToa = 1;
        int indexThuoc = 1;
        for (int index = firstSheet.getLastRowNum(); index > firstSheet.getFirstRowNum(); index--) {
            if(firstSheet.getRow(index) != null){
                firstSheet.removeRow( firstSheet.getRow(index));}
        }
        for (int index = secondSheet.getLastRowNum(); index > secondSheet.getFirstRowNum(); index--) {
            if(secondSheet.getRow(index) != null){
                secondSheet.removeRow( secondSheet.getRow(index));}
        }
        //rewrite
        for(ToaThuoc x: saveList){
            firstSheet.createRow(indexToa);
            Row addRowToa = firstSheet.getRow(indexToa);
            Cell cellToa;
            cellToa = addRowToa.createCell(0);
            cellToa.setCellValue(x.getPresID());
            cellToa = addRowToa.createCell(1);
            cellToa.setCellValue(x.getName());
            cellToa = addRowToa.createCell(2);
            cellToa.setCellValue(x.getEndDate());
            cellToa.setCellStyle(cellStyle);
            cellToa = addRowToa.createCell(3);
            cellToa.setCellValue(x.getStartedDate());
            cellToa.setCellStyle(cellStyle);
            for(ThuocTrongToa i : x.getListProduct()) {
                secondSheet.createRow(indexThuoc);
                Row addRowThuoc = secondSheet.getRow(indexThuoc);
                Cell cell;
                cell = addRowThuoc.createCell(0);
                cell.setCellValue(i.getProductID());
                cell = addRowThuoc.createCell(1);
                cell.setCellValue(i.getName());
                cell = addRowThuoc.createCell(2);
                cell.setCellValue(i.getUnit());
                cell = addRowThuoc.createCell(3);
                cell.setCellValue(i.getLieu());
                cell = addRowThuoc.createCell(4);
                cell.setCellValue(x.getPresID());
                indexThuoc++;
            }
            indexToa++;
        }
        FileOutputStream fileOut = new FileOutputStream(excelPath);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
        inputStream.close();
    }
}