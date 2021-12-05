

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class MapFileReader {

	public void readFile() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("HouseMap.xml"));
			
			document.getDocumentElement().normalize();
			
			NodeList rooms = document.getElementsByTagName("room");
			for(int i = 0; i < rooms.getLength(); i++) {
				Node room = rooms.item(i);
				if(room.getNodeType() == Node.ELEMENT_NODE) {
					Element roomElement = (Element) room;
					//System.out.println("Room name: "+ roomElement.getAttribute("name"));
				}
			}
			
		}catch(ParserConfigurationException e){
			e.printStackTrace();
		}catch(SAXException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
