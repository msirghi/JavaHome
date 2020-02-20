package thirdHomework.xml_parsing;

import org.w3c.dom.*;
import thirdHomework.Constants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class JDOMManipulation {
  private static final String FILE_PATH = "./files/xml/employees.txt";

  private static void displayElement(String text, String tagName, Element element, int id) {
    System.out.println(text + element
        .getElementsByTagName(tagName)
        .item(id)
        .getTextContent());
  }

  public static void main(String[] args) {
    parseFile();
//    queryFile(4);
  }

  private static void parseFile() {
    try {
      File inputFile = new File(FILE_PATH);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();
      NodeList departmentList = doc.getElementsByTagName(Constants.DEPT_TAG);

      for (int temp = 0; temp < departmentList.getLength(); temp++) {
        Node nNode = departmentList.item(temp);
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;
          System.out.println("Department name: " + eElement.getAttribute(Constants.DEPT_NAME));
          System.out.println("Department id: " + eElement.getAttribute(Constants.DEPT_ID));
          System.out.println("---------------\n");

          NodeList employeeList = ((Element) nNode).getElementsByTagName(Constants.EMPLOYEE_TAG);
          for (int i = 0; i < employeeList.getLength(); i++) {
            displayRoutine(eElement, i, employeeList);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void queryFile(int employeeId) {
    try {
      File inputFile = new File(FILE_PATH);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();
      NodeList departmentList = doc.getElementsByTagName(Constants.DEPT_TAG);

      for (int deptCounter = 0; deptCounter < departmentList.getLength(); deptCounter++) {
        Node nNode = departmentList.item(deptCounter);
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;
          NodeList employeeList = ((Element) nNode).getElementsByTagName(Constants.EMPLOYEE_TAG);

          for (int empCounter = 0; empCounter < employeeList.getLength(); empCounter++) {
            NamedNodeMap map = employeeList.item(empCounter).getAttributes();
            if (map.getNamedItem(Constants.EMP_ID).getTextContent().equals("00" + employeeId)) {
              displayRoutine(eElement, empCounter, employeeList);
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void displayRoutine(Element element, int counter, NodeList list) {
    displayElement("First name: ", Constants.FIRST_NAME, element, counter);
    displayElement("Last name: ", Constants.LAST_NAME, element, counter);
    displayElement("Birth date: ", Constants.BIRTH_DATE, element, counter);
    displayElement("Position: ", Constants.POSITION, element, counter);

    System.out.println("Skills: ");
    try {
      Node skills = list.item(counter);
      Element skill = (Element) skills;
      NodeList skillList = ((Element) skills).getElementsByTagName(Constants.SKILL);
      for (int skillCounter = 0; skillCounter < skillList.getLength(); skillCounter++) {
        displayElement("- ", Constants.SKILL, skill, skillCounter);
      }
      System.out.println();
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }
}
