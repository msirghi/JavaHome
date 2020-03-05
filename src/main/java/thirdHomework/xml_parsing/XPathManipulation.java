package thirdHomework.xml_parsing;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class XPathManipulation {
  public static void main(String[] args) throws Exception {
    Document document = getDocument("files/xml/employees.txt");
    System.out.println(checkIfNodeExists(document, "company/department/employee/firstName"));
  }

  private static boolean checkIfNodeExists(Document document, String xpathExpression) {
    boolean matches = false;
    XPathFactory xpathFactory = XPathFactory.newInstance();
    XPath xpath = xpathFactory.newXPath();

    try {
      XPathExpression expr = xpath.compile(xpathExpression);
      NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
      if (nodes != null && nodes.getLength() > 0) {
        matches = true;
      }
    } catch (XPathExpressionException e) {
      e.printStackTrace();
    }
    return matches;
  }

  private static Document getDocument(String fileName) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    return builder.parse(fileName);
  }
}
