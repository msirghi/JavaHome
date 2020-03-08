package thirdHomework.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Employee {
  @JsonProperty("empId")
  private int empId;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("birthDate")
  private String birthDate;

  @JsonProperty("position")
  private String position;

  @JsonProperty("skills")
  private List<String> skills;

  @JsonProperty("managerId")
  private int managerId;

}
