package thirdHomework.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.Writer;

public class OpenCSVManipulation {
  private static final String STRING_ARRAY_SAMPLE = "./files/csv/openCSV.csv";

  public static void main(String[] args) throws IOException {
    try (
        Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));

        CSVWriter csvWriter = new CSVWriter(writer,
            CSVWriter.DEFAULT_SEPARATOR,
            CSVWriter.NO_QUOTE_CHARACTER,
            CSVWriter.DEFAULT_ESCAPE_CHARACTER,
            CSVWriter.DEFAULT_LINE_END);
    ) {
      String[] headerRecord = {"ID", "Name", "Group", "University"};
      csvWriter.writeNext(headerRecord);

      csvWriter.writeNext(new String[]{"1", "First", "321", "USM"});
      csvWriter.writeNext(new String[]{"2", "Second", "123", "UTM"});
    }
    parseFile();
  }

  private static void parseFile() {
    try (
        Reader reader = Files.newBufferedReader(Paths.get(STRING_ARRAY_SAMPLE));
        CSVReader csvReader = new CSVReader(reader);
    ) {
      String[] nextRecord;
      boolean firstRow = true;
      while ((nextRecord = csvReader.readNext()) != null) {
        if (!firstRow) {

          System.out.println("ID : " + nextRecord[0]);
          System.out.println("Name : " + nextRecord[1]);
          System.out.println("Group : " + nextRecord[2]);
          System.out.println("University : " + nextRecord[3]);
          System.out.println("==========================");
        } else {
          firstRow = false;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}