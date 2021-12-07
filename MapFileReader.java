

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
import exceptions.TagNameException;


public class MapFileReader {
	
	 private RoomList list = RoomList.getInstance();

	public void readFile() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("HouseMap.xml"));
			verifyTags(document);
			document.getDocumentElement().normalize();
			
			NodeList rooms = document.getElementsByTagName("room");
			constructRoomList(rooms);
			setStartRoom(rooms);
			
			for (int i = 0; i < rooms.getLength(); i++) {
				Node room = rooms.item(i);
				if(room.getNodeType() == Node.ELEMENT_NODE) {
					Element roomElement = (Element) room;
					NodeList roomExitsList = roomElement.getChildNodes();
					constructExits(roomElement.getAttribute("name"), roomExitsList);
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
	
	private void constructRoomList(NodeList rooms) {
		for(int i = 0; i < rooms.getLength(); i++) {
			Node room = rooms.item(i);
			if(room.getNodeType() == Node.ELEMENT_NODE) {
				Element roomElement = (Element) room;
				Room newHouseRoom = new Room(roomElement.getAttribute("name"));
				list.addRoom(newHouseRoom);
			}
		}
	}
	
	private void setStartRoom(NodeList rooms) {
		for(int i = 0; i < rooms.getLength(); i++) {
			Node room = rooms.item(i);
			if(room.getNodeType() == Node.ELEMENT_NODE) {
				Element roomElement = (Element) room;
				String current = roomElement.getAttribute("start");
				if(current.equals("true")) {
					list.setStartRoom(roomElement.getAttribute("name"));
				}
			}
		}
	}
	
	private void constructExits(String roomName, NodeList exits) {
		for(int i = 0; i < exits.getLength(); i++) {
			Node exit = exits.item(i);
			if(exit.getNodeType() == Node.ELEMENT_NODE) {
				Element exitElement = (Element) exit;
				list.updateRoomsListExits(roomName, exitElement.getTagName(), exitElement.getTextContent());
			}
		}
	}
	
	private void verifyTags(Document documentoXml){
        try{
            NodeList tags = documentoXml.getElementsByTagName("*");
            for(int i=0;i<tags.getLength();i++){
            	String tagName = tags.item(i).getNodeName();
                if(!isCorrectTag(tagName)) {
                	throw new TagNameException("Wrong tag found: " + tagName);
                }
            }
        }catch(TagNameException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
	
	private boolean isCorrectTag(String tagName) {
		return (tagName.equals("house") || tagName.equals("room") || tagName.equals("north") ||
				tagName.equals("east") || tagName.equals("south") || tagName.equals("west"));
	}
}
