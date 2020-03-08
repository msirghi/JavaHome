package thirdHomework.xml_parsing;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import thirdHomework.Constants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JDOMManipulation {
  private static final String FILE_PATH = "./files/xml/employees.txt";
  private Map<String, Integer> managerRefs = new HashMap<>();

  private static void displayElement(String text, String tagName, Element element, int id) {
    System.out.println(text + element
        .getElementsByTagName(tagName)
        .item(id)
        .getTextContent());
  }

  public static void main(String[] args) {
//    parseFile();
//    queryFile(2);
    addData();
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
    displayElement("ManagerID: ", Constants.MANAGER_ID, element, counter);

    System.out.println("Skills: ");
    Node skills = list.item(counter);
    Element skill = (Element) skills;
    NodeList skillList = ((Element) skills).getElementsByTagName(Constants.SKILL);
    for (int skillCounter = 0; skillCounter < skillList.getLength(); skillCounter++) {
      displayElement("- ", Constants.SKILL, skill, skillCounter);
    }
    System.out.println();
  }

  private static void addData() {
    try {
      File fXmlFile = new File(FILE_PATH);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
      Element nList = doc.getDocumentElement();

      Element newDepartment = doc.createElement(Constants.DEPT_TAG);
      Attr depIdAttribute = doc.createAttribute(Constants.DEPT_ID);
      depIdAttribute.setValue("003");
      newDepartment.setAttributeNode(depIdAttribute);

      Attr nameAttribute = doc.createAttribute(Constants.DEPT_NAME);
      nameAttribute.setValue("Finances");
      newDepartment.setAttributeNode(nameAttribute);

      Element newEmployee = doc.createElement(Constants.EMPLOYEE_TAG);
      Attr employeeId = doc.createAttribute(Constants.EMP_ID);
      employeeId.setValue("005");
      newEmployee.setAttributeNode(employeeId);
      newDepartment.appendChild(newEmployee);

      Element employeeLastName = doc.createElement(Constants.LAST_NAME);
      employeeLastName.appendChild(doc.createTextNode("Fifth last name"));
      newEmployee.appendChild(employeeLastName);

      Element employeeFirstName = doc.createElement(Constants.FIRST_NAME);
      employeeFirstName.appendChild(doc.createTextNode("Fifth first name"));
      newEmployee.appendChild(employeeFirstName);

      Element employeeBirthDate = doc.createElement(Constants.BIRTH_DATE);
      employeeBirthDate.appendChild(doc.createTextNode("02.02.2002"));
      newEmployee.appendChild(employeeBirthDate);

      Element employeePosition = doc.createElement(Constants.POSITION);
      employeePosition.appendChild(doc.createTextNode("Developer"));
      newEmployee.appendChild(employeePosition);

      Element employeeManagerId = doc.createElement(Constants.MANAGER_ID);
      employeeManagerId.appendChild(doc.createTextNode("001"));
      newEmployee.appendChild(employeeManagerId);

      Element skills = doc.createElement(Constants.SKILLS_TAG);
      Element firstSkill = doc.createElement(Constants.SKILL);
      Element secondSkill = doc.createElement(Constants.SKILL);
      firstSkill.appendChild(doc.createTextNode("Working hard"));
      secondSkill.appendChild(doc.createTextNode("Next skill"));

      skills.appendChild(firstSkill);
      skills.appendChild(secondSkill);
      newEmployee.appendChild(skills);
      nList.appendChild(newDepartment);

      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.transform(new DOMSource(doc), new StreamResult(new File(FILE_PATH)));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
