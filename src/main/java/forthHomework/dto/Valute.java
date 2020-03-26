package forthHomework.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@XStreamAlias("Valute")
@NoArgsConstructor
public class Valute {

  @XStreamAsAttribute
  @XStreamAlias("ID")
  private Integer id;

  @XStreamAlias("NumCode")
  private Integer numCode;

  @XStreamAlias("CharCode")
  private String charCode;

  @XStreamAlias("Name")
  private String name;

  @XStreamAlias("Nominal")
  private Integer nominal;

  @XStreamAlias("Value")
  private String value;

  public Valute(Integer numCode, String charCode, String name, String value, Integer nominal, Integer id) {
    this.numCode = numCode;
    this.charCode = charCode;
    this.name = name;
    this.value = value;
    this.nominal = nominal;
    this.id = id;
  }
}
