package secondHomework.parsing;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class OpenCSVExample {

  public static void main(String[] args) {
    CSVReader reader = null;
    try {
      reader = new CSVReader(new FileReader("files/input.csv"), ',');
      String[] nextLine;
      while ((nextLine = reader.readNext()) != null) {
        for (String token : nextLine) {
          System.out.print(token);
        }
        System.out.println();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
