package thirdHomework.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


public class STAXManipulation {
  public static void main(String[] args) {
    parseFile();
//    queryFile("002");
  }

  private static void queryFile(String empId) {
    boolean bFirstName = false;
    boolean bLastName = false;
    boolean bNickName = false;
    boolean bMarks = false;
    boolean skills = false;
    boolean employeeFound = false;
    List list = new ArrayList<>();

    try {
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLEventReader eventReader =
          factory.createXMLEventReader(new FileReader("files/xml/employees.txt"));

      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();
        switch (event.getEventType()) {
          case XMLStreamConstants.START_ELEMENT:
            StartElement startElement = event.asStartElement();
            String qName = startElement.getName().getLocalPart();
            if (qName.equalsIgnoreCase("employee")) {
              Iterator<Attribute> attributes = startElement.getAttributes();
              employeeFound = attributes.next().getValue().equals(empId);
            } else if (qName.equalsIgnoreCase("lastName") && employeeFound) {
              bFirstName = true;
            } else if (qName.equalsIgnoreCase("firstName") && employeeFound) {
              bLastName = true;
            } else if (qName.equalsIgnoreCase("birthDate") && employeeFound) {
              bNickName = true;
            } else if (qName.equalsIgnoreCase("position") && employeeFound) {
              bMarks = true;
            } else if (qName.equalsIgnoreCase("skill") && employeeFound) {
              skills = true;
            }
            break;

          case XMLStreamConstants.CHARACTERS:
            if (employeeFound) {
              Characters characters = event.asCharacters();
              list.add(characters.getData());
              if (bFirstName) {
                System.out.println("First Name: " + characters.getData());
                bFirstName = false;
              }
              if (bLastName) {
                System.out.println("Last Name: " + characters.getData());
                bLastName = false;
              }
              if (bNickName) {
                System.out.println("Birth Day: " + characters.getData());
                bNickName = false;
              }
              if (bMarks) {
                System.out.println("Position: " + characters.getData());
                bMarks = false;
              }
              if (skills) {
                System.out.println("Skill: " + characters.getData());
                skills = false;
              }
              break;
            }
        }
      }
    } catch (FileNotFoundException | XMLStreamException e) {
      e.printStackTrace();
    }
  }

  private static void parseFile() {
    boolean bFirstName = false;
    boolean bLastName = false;
    boolean bNickName = false;
    boolean bMarks = false;
    boolean skills = false;
    List list = new ArrayList();

    try {
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLEventReader eventReader =
          factory.createXMLEventReader(new FileReader("files/xml/employees.txt"));

      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();
        switch (event.getEventType()) {
          case XMLStreamConstants.START_ELEMENT:
            StartElement startElement = event.asStartElement();
            String qName = startElement.getName().getLocalPart();
            if (qName.equalsIgnoreCase("department")) {
              Iterator<Attribute> attributes = startElement.getAttributes();

              String deptName = attributes.next().getValue();
              String deptId = attributes.next().getValue();

              System.out.println("Department name: " + deptName);
              System.out.println("Department id: " + deptId);

            } else if (qName.equalsIgnoreCase("lastName")) {
              bFirstName = true;
            } else if (qName.equalsIgnoreCase("firstName")) {
              bLastName = true;
            } else if (qName.equalsIgnoreCase("birthDate")) {
              bNickName = true;
            } else if (qName.equalsIgnoreCase("position")) {
              bMarks = true;
            } else if (qName.equalsIgnoreCase("skill")) {
              skills = true;
            }
            break;

          case XMLStreamConstants.CHARACTERS:
            Characters characters = event.asCharacters();
            list.add(characters.getData());

            if (bFirstName) {
              System.out.println("First Name: " + characters.getData());
              bFirstName = false;
            }
            if (bLastName) {
              System.out.println("Last Name: " + characters.getData());
              bLastName = false;
            }
            if (bNickName) {
              System.out.println("Birth Day: " + characters.getData());
              bNickName = false;
            }
            if (bMarks) {
              System.out.println("Position: " + characters.getData());
              bMarks = false;
            }
            if (skills) {
              System.out.println("Skill: " + characters.getData());
              skills = false;
            }
            break;
        }
      }

      System.out.println("List is: " + list);
    } catch (FileNotFoundException | XMLStreamException e) {
      e.printStackTrace();
    }
  }
}
