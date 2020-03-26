package forthHomework.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XStreamAlias("ValCurs")
@ToString
@NoArgsConstructor
public class ValCurs {

  public ValCurs(String date, String name, List<Valute> valuteList) {
    this.date = date;
    this.name = name;
    this.valuteList = valuteList;
  }

  @XStreamAsAttribute
  @XStreamAlias("Date")
  private String date;

  @XStreamAsAttribute
  @XStreamAlias("name")
  private String name;

  @XStreamAlias("ValCurs")
  private List<Valute> valuteList = new ArrayList<>();

}
