package thirdHomework.csv;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommonCSVManipulation {
  private static final String SAMPLE_CSV_FILE = "./files/csv/commonCSV.csv";

  public static void main(String[] args) throws IOException {
    try (
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));

        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
            .withHeader("ID", "Name", "Group", "University"));
    ) {
      csvPrinter.printRecord("1", "First", "3.4TI", "USM");
      csvPrinter.printRecord("2", "Second", "1231", "UTM");
      csvPrinter.printRecord("3", "Third", "2.4TI", "USM");
      csvPrinter.flush();
      parseFile();
    }
  }

  private static void parseFile() {
    try (
        Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    ) {
      boolean fistRow = true;
      for (CSVRecord csvRecord : csvParser) {
        if (!fistRow) {
          fistRow = true;
          String id = csvRecord.get(0);
          String name = csvRecord.get(1);
          String group = csvRecord.get(2);
          String university = csvRecord.get(3);

          System.out.println("---------------");
          System.out.println("Name : " + id);
          System.out.println("Email : " + name);
          System.out.println("Phone : " + group);
          System.out.println("Country : " + university);
          System.out.println("---------------\n\n");
        } else {
          fistRow = false;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
