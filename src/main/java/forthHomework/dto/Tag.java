package forthHomework.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class Tag {
  public Tag(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  private Long id;

  private String name;
}
