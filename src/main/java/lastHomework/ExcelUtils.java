package lastHomework;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

  private static XSSFWorkbook workbook;
  private static XSSFSheet sheet;

  public ExcelUtils(String excelPath, String sheetName) {
    try {
      workbook = new XSSFWorkbook(excelPath);
      sheet = workbook.getSheet(sheetName);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public int getRowCount() {
    return sheet.getPhysicalNumberOfRows();
  }

  public int getColumnCount() {
    return sheet.getRow(0).getPhysicalNumberOfCells();
  }

  public String getCellDataString(int row, int col) {
    return sheet.getRow(row).getCell(col).getStringCellValue();
  }
}
