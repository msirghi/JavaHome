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
public class Company {

  @JsonProperty("departments")
  private List<Department> departments = new ArrayList<>();

  public void addDepartment(Department department) {
    this.departments.add(department);
  }

}
