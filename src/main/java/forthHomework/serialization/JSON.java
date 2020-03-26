package forthHomework.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import forthHomework.dto.Category;
import forthHomework.dto.Pet;
import forthHomework.dto.Tag;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JSON {
  private static final String FILE_PATH = "files/testJSON.json";
  public static void main(String[] args) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      objectMapper.writeValue(
              new File(FILE_PATH),
              Pet
                      .builder()
                      .id(1L)
                      .status("ACTIVE")
                      .name("NAME")
                      .category(Category.builder().id(1L).name("Category 1").build())
                      .tags(new ArrayList<Tag>() {
                        {
                          add(new Tag(1L, "Tag 1"));
                          add(new Tag(2L, "Tag 2"));
                        }
                      })
                      .build());
      System.out.println("File successfully created.");

      System.out.println("=================");
      System.out.println(objectMapper.readValue(new File(FILE_PATH), Pet.class));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
