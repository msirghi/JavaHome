package forthHomework.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
public class Pet {

  public Pet(Long id, Category category, String name, List<String> photoUrls, List<Tag> tags, String status) {
    this.id = id;
    this.category = category;
    this.name = name;
    this.photoUrls = photoUrls;
    this.tags = tags;
    this.status = status;
  }

  private Long id;

  private Category category;

  private String name;

  private List<String> photoUrls = new ArrayList<>();

  private List<Tag> tags = new ArrayList<>();

  private String status;
}
