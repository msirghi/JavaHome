package thirdHomework.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import thirdHomework.Constants;

public class XPathManipulation {
  public static void main(String[] args) throws Exception {
    Document document = getDocument(Constants.EMPLOYEES_TXT_PATH);
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
