package code_refactoring_q2_2022;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class BuildXML {
	
   public static void main(String args[]) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
	   BuildXML buildXML = new BuildXML();
	   buildXML.buildXmlFile();
   }
   
   public Document buildDocument() throws ParserConfigurationException{
	   return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
   }
   
   public Element createElement(Document doc, String elementName){
	   return doc.createElement(elementName);
   }
   
   public Attr createAttribute(Document doc, String type, String value) {
	   Attr attr = doc.createAttribute(type);
	   attr.setValue(value);
	   return attr;
   }
   
   public Element appendTextNode(Document doc, Element element, String textNode){
	   element.appendChild(doc.createTextNode(textNode));
	   return element;
   }
   
   public Element setAttributeForElement(Element element, Attr attribute){
	   element.setAttributeNode(attribute);
	   return element ; 
   }
   
   public void transformToXml(Document doc) throws TransformerFactoryConfigurationError, TransformerException{
	   Transformer transformer = TransformerFactory.newInstance().newTransformer();
	   DOMSource source = new DOMSource(doc);
	   transformer.transform(source, new StreamResult(new File("students.xml")));
	   transformer.transform(source, new StreamResult(System.out));   
   }
   
   public void buildXmlFile() throws ParserConfigurationException,TransformerFactoryConfigurationError, TransformerException{
	   Document doc = buildDocument();
	  
	   Element employee = setAttributeForElement(createElement(doc,"Employee"),createAttribute(doc,"Gender","Male"));
	   
	   Element name = setAttributeForElement(appendTextNode(doc,createElement(doc,"Name"),"Nalaka Dissanayake"),createAttribute(doc,"Initials","S. A."));
	   Element address = setAttributeForElement(appendTextNode(doc,createElement(doc,"Address"),"No:115/1, Avenue Street, Kandy"),createAttribute(doc,"No","115/1"));
	   setAttributeForElement(address,createAttribute(doc,"Street","Avenue Street"));
	   
	   employee.appendChild(name);
	   employee.appendChild(address);
	   
	   doc.appendChild(createElement(doc,"School")).appendChild(createElement(doc,"Students")).appendChild(employee);

	   transformToXml(doc);
   }
}
