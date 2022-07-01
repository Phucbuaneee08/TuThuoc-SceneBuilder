package Medicine;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFileDemo
{
    private final String excelPath = "F:\\TuThuoc-SceneBuilder\\Medicine\\Source\\QLTuThuoc.xlsx";
    public  ArrayList<Product> getExcelFileDemo() throws IOException,Exception
    {
        ArrayList<Product> list = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        InputStream inputStream =  new FileInputStream(new File(excelPath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        Row firstRow = iterator.next();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if(!isRowEmpty(nextRow)){
                int productID =(int)nextRow.getCell(0).getNumericCellValue();
                String name = nextRow.getCell(1).getStringCellValue();
                int quantity = (int)nextRow.getCell(2).getNumericCellValue();
                String unit = nextRow.getCell(3).getStringCellValue();
                String effect = nextRow.getCell(4).getStringCellValue();
                Date expiredDate = nextRow.getCell(5).getDateCellValue() ;
                String link = nextRow.getCell(7).getStringCellValue();
                list.add(new Thuoc(productID,name,quantity,link,unit,expiredDate,effect));
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
        for(Product x: saveList){
            int lastIndex = firstSheet.getLastRowNum();
            firstSheet.createRow(lastIndex);
            Row addRow = firstSheet.getRow(lastIndex);
            Cell cell;
            cell = addRow.createCell(0);
            cell.setCellValue((double)lastIndex);
            cell =addRow.createCell(1);
            cell.setCellValue(x.getName());
            cell = addRow.createCell(2);
            cell.setCellValue(x.getQuantity());
            cell =addRow.createCell(3);
            cell.setCellValue(x.getUnit());
            cell =addRow.createCell(4);
            cell.setCellValue(((Thuoc) x).getEffect().getName());
            cell =addRow.createCell(5);
            cell.setCellValue(new Date());
            cell = addRow.createCell(7);
            cell.setCellValue(x.getLink());
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
        return firstSheet.getLastRowNum();
    }
}
