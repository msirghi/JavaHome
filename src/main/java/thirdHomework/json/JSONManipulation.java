package thirdHomework.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import thirdHomework.Constants;
import thirdHomework.pojos.Company;
import thirdHomework.pojos.Department;
import thirdHomework.pojos.Employee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

@Getter
public class JSONManipulation {
  private Company company;

  public JSONManipulation() {
    this.readJson(Constants.OLD_JSON_PATH);
    this.createJson(Constants.NEW_JSON_PATH);
  }

  public void readJson(String filename) {
    try {
      File inputFile = new File(filename);
      ObjectMapper mapper = new ObjectMapper();
      this.company = mapper.readValue(inputFile, Company.class);
      System.out.println(company.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void createJson(String newJsonPath) {
    try {
      FileWriter outputFile = new FileWriter(newJsonPath);
      ObjectMapper mapper = new ObjectMapper();
      Company company = new Company();

      for (Department department : this.company.getDepartments()) {
        if (department.getDepId() == 1) {
          Department newDepartment = new Department();
          for (Employee employee : department.getEmployees()) {
            if (employee.getEmpId() == 1) {
              newDepartment.setDepId(department.getDepId());
              newDepartment.setName(department.getName());
              newDepartment.addEmployee(employee);
            }
          }
          company.addDepartment(newDepartment);
        }
      }

      BufferedWriter writer = new BufferedWriter(outputFile);
      writer.write(mapper.writeValueAsString(company));
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new JSONManipulation();
  }
}
