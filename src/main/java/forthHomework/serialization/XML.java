package forthHomework.serialization;

import com.thoughtworks.xstream.XStream;
import forthHomework.dto.ValCurs;
import forthHomework.dto.Valute;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class XML {
  public static void main(String[] args) {
    ValCurs valCurs = new ValCurs("25.05.2022", "Name", new ArrayList<Valute>() {
      {
        add(new Valute(978, "EUR", "Euro", "20.1", 1, 48));
        add(new Valute(978, "EUR", "Euro", "20.1", 1, 49));
      }
    });

    XStream xStream = new XStream();
    xStream.alias("ValCurs", ValCurs.class);
    xStream.alias("Valute", Valute.class);

    xStream.ignoreUnknownElements();
    xStream.processAnnotations(ValCurs.class);
    xStream.processAnnotations(Valute.class);

    String output = xStream.toXML(valCurs);
    System.out.println(output);
    try (PrintStream out = new PrintStream(new FileOutputStream("files/xml/bnmXML.txt"))) {
      out.print(output);

      System.out.println("================");
      FileReader reader = new FileReader("files/xml/bnmXML.txt");
      ValCurs result = (ValCurs) xStream.fromXML(reader);
      System.out.println(result.toString());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
