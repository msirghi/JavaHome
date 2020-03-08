package thirdHomework.xml;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import thirdHomework.Constants;
import thirdHomework.json.JSONManipulation;
import thirdHomework.pojos.Department;
import thirdHomework.pojos.Employee;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class ApachePOIManipulation {

  public static void main(String[] args) {
    JSONManipulation jsonParser = new JSONManipulation();
    jsonParser.readJson(Constants.OLD_JSON_PATH);
    XSSFWorkbook workbook = new XSSFWorkbook();

    for (Department department : jsonParser.getCompany().getDepartments()) {
      XSSFSheet sheet = workbook.createSheet(department.getName());
      int colNum = 0;
      int rowNum = 1;
      Row fieldSet = sheet.createRow(0);

      for (Field field : Employee.class.getDeclaredFields()) {
        Cell cell = fieldSet.createCell(colNum++);
        cell.setCellValue(field.getName());
      }

      for (Employee employee : department.getEmployees()) {
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(employee.getEmpId());
        row.createCell(1).setCellValue(employee.getLastName());
        row.createCell(2).setCellValue(employee.getFirstName());
        row.createCell(3).setCellValue(employee.getBirthDate());
        row.createCell(4).setCellValue(employee.getPosition());
        row.createCell(5).setCellValue(employee.getManagerId());

        StringBuilder skills = new StringBuilder();
        for (String skill : employee.getSkills()) {
          skills.append(skill).append(", \n");
        }
        row.createCell(6).setCellValue(skills.toString());
      }
    }

    try {
      FileOutputStream outputStream = new FileOutputStream(Constants.XLSX_FILE_NAME);
      workbook.write(outputStream);
      workbook.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}