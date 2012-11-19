import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.util.*;


public class XML {	

public XML() {
}	
	
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document parseDoc;
	private Document newDoc;
	private TransformerFactory transformerFactory;
	private Transformer transformer;
	private DOMSource source;
	private PrintStream ps;
	private StreamResult result;
	
	// Create all variables required for methods perfoming XML I/O and highscore analysis
	// **Note to Self: These cannot be placed in the constructors of methods as they are used in other classes??**
	private String element;
	private Element em;
	private int scorecount;	
	private Node scoreNode;	
	private Node root;
	private Element rootElement;
	private String rootline;


	private void initialiseXML(int initSwitch) throws FileNotFoundException,ParserConfigurationException,Exception {
	
	
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
			switch (initSwitch) {
				// If 'initSwitch' = 0, then parse the existing file and retrieve the root element..
				// ..and then read the 'number' element to determine how many highscore records are currently in the xml file
				case 0:	parseDoc = documentBuilder.parse("Test.xml");
						root = parseDoc.getFirstChild();
						break;
				// If 'initSwitch' = 1, then create a new xml document and within that create a new root element called 'scoreroot'.. 
				// ..append this new root element to the new document ready for highscore elements to be added	
				case 1:	newDoc = documentBuilder.newDocument();
						rootline = "scoreroot";
						rootElement = newDoc.createElement(rootline);
						newDoc.appendChild(rootElement);
						break;
			}
		}		
				
	public void addToXML(String TestString) throws Exception{
	
			initialiseXML(1);

			
			element = "testel";
			em = newDoc.createElement(element);
			em.appendChild(newDoc.createTextNode(TestString));
			rootElement.appendChild(em);
				
			writeXML();
	}
	
	private void writeXML() throws Exception {
		
		transformerFactory = TransformerFactory.newInstance();
        transformer = transformerFactory.newTransformer();
        
		source = new DOMSource(newDoc);
		
		
		
		// Set XML output settings
		ps = new PrintStream("Test.xml");
		result =  new StreamResult(ps);
		
		// Output to file using settings
		transformer.transform(source, result);
	
	}
	
	public void readXML() throws NullPointerException, Exception {
	   
		
		   
				initialiseXML(0);
		
				// Retrieve the current high score element and separate the information
				scoreNode = parseDoc.getElementsByTagName("testel").item(0);
				System.out.println("XML output: " + scoreNode.getTextContent());
				
		
	}
}
