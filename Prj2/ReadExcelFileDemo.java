package Prj2;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private final String excelPath = "C:\\DevC++\\ViscodeCC++\\.vscode\\FirstJavaFxProject\\FirstJavaFxProject\\src\\Prj2\\Source\\QLTuThuoc.xlsx";
    public  ArrayList<Product> getExcelFileDemo() throws IOException
    {
        ArrayList<Product> list = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
       
        InputStream inputStream =  new FileInputStream(new File(excelPath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        iterator.next();
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
            list.add(new Thuoc(productID,name,quantity,link,unit,expiredDate,effect));}
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
}