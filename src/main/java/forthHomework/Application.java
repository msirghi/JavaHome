package forthHomework;

import com.thoughtworks.xstream.XStream;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Application {
  public static void main(String[] args) {
    try {
      XStream xStream = new XStream();
      xStream.ignoreUnknownElements();
      xStream.processAnnotations(Config.class);
      xStream.processAnnotations(Server.class);

      System.out.println("================");
      FileReader reader = new FileReader("files/xml/testFile.txt");
      Config result = (Config) xStream.fromXML(reader);
      System.out.println(result.toString());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
