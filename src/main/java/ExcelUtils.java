
/*
 * Custom excel reader class that uses
 * apache poi. 
 */

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
public class ExcelUtils {
    
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    
    public ExcelUtils(String excelPath, String sheetName) throws Exception {
        workbook = new XSSFWorkbook(excelPath);
        sheet = workbook.getSheet(sheetName);
    }
    
    public int getRowCount() throws IOException {
        
        //String excelPath = "./data/CovidPokerPayoutSheet.xlsx";
        
        int rowCount = sheet.getPhysicalNumberOfRows();
        return rowCount;
    }
    
    public Object getCellDataInt(int row, int col) throws IOException {
        
        if ( row < 0 || col < 0) {
            throw new IllegalArgumentException("row or column value is negative");
        }
        
        DataFormatter formatter = new DataFormatter();
        Object retVal = formatter.formatCellValue(sheet.getRow(row).getCell(col));
        
        return retVal;
    }
    
    
}
