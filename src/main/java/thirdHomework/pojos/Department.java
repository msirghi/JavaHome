package thirdHomework.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Department {

  @JsonProperty("depId")
  private int depId;

  @JsonProperty("name")
  private String name;

  @JsonProperty("employees")
  private List<Employee> employees = new ArrayList<>();

  public void addEmployee(Employee employee) {
    this.employees.add(employee);
  }
}