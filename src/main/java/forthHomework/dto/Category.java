package forthHomework.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class Category {

  public Category(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  private Long id;

  private String name;
}
