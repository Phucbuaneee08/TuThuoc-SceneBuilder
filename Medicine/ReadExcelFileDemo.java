package Medicine;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
                String link = nextRow.getCell(7).getStringCellValue();
                list.add(new Thuoc(productID,name,quantity,link,unit,expiredDate,effect));
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
//                String link = nextRow.getCell(5).getStringCellValue();
                list.add(new DungCu(productID,name,quantity,"none",unit,effect));
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
        int lastIndexThuoc = 1;
        int lastIndexDC = 1;
        for(Product x: saveList){
            if(x instanceof Thuoc){
                firstSheet.createRow(lastIndexThuoc);
                Row addRowThuoc = firstSheet.getRow(lastIndexThuoc);
                Cell cell;
                cell = addRowThuoc.createCell(0);
                cell.setCellValue((double)lastIndexThuoc);
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
                cell.setCellValue(x.getLink());
                lastIndexThuoc++;
            } else if(x instanceof DungCu){
                secondSheet.createRow(lastIndexDC);
                Row addRowDC = secondSheet.getRow(lastIndexDC);
                Cell cell;
                cell = addRowDC.createCell(0);
                cell.setCellValue((double)lastIndexDC);
                cell =addRowDC.createCell(1);
                cell.setCellValue(x.getName());
                cell = addRowDC.createCell(2);
                cell.setCellValue(x.getQuantity());
                cell =addRowDC.createCell(3);
                cell.setCellValue(x.getUnit());
                cell =addRowDC.createCell(4);
                String effect = ((DungCu) x).getUse();
                cell.setCellValue(effect);
                lastIndexDC++;
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
