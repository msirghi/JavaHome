package thirdHomework.xml_parsing;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class JDOMManilupation {
  private static final String FILE_PATH = "./files/xml/employees.txt";

  private static void displayElement(String text, String tagName, Element element, int id) {
    System.out.println(text + element
        .getElementsByTagName(tagName)
        .item(id)
        .getTextContent());
  }

  public static void main(String[] args) {
    parseFile();
  }

  private static void parseFile() {
    try {
      File inputFile = new File(FILE_PATH);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();
      NodeList departmentList = doc.getElementsByTagName("department");

      for (int temp = 0; temp < departmentList.getLength(); temp++) {
        Node nNode = departmentList.item(temp);
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;
          System.out.println("Department name: " + eElement.getAttribute("name"));
          System.out.println("Department id: " + eElement.getAttribute("depId"));
          System.out.println("---------------\n");

          NodeList employeeList = ((Element) nNode).getElementsByTagName("employee");
          for (int i = 0; i < employeeList.getLength(); i++) {
            displayElement("First name: ", "firstName", eElement, i);
            displayElement("Last name: ", "lastName", eElement, i);
            displayElement("Birth Date: ", "birthDate", eElement, i);
            displayElement("Position: ", "position", eElement, i);

            System.out.println("Skills: ");
            Node skills = employeeList.item(i);
            NodeList skillList = ((Element) skills).getElementsByTagName("skills");

            Element keks = (Element) skills;

            for (int kek = 0; kek < skillList.getLength(); kek++) {
              displayElement("Keks: ", "skill", keks, kek);
            }
            System.out.println();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
