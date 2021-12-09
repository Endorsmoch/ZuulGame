package game;

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

	public boolean readFile() {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("HouseMap.xml"));
	
			if(verifyTags(document)) {
				document.getDocumentElement().normalize();
				NodeList rooms = document.getElementsByTagName("room");
				constructRoomList(rooms);
				setStartRoom(rooms);
				constructExits(rooms);
				return true;
			}
		}catch(ParserConfigurationException e){
			e.printStackTrace();
		}catch(SAXException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void constructRoomList(NodeList rooms) {
		for(int i = 0; i < rooms.getLength(); i++) {
			Node room = rooms.item(i);
			if(isAnElementNode(room)) {
				Element roomElement = (Element) room;
				Room newHouseRoom = new Room(roomElement.getAttribute("name"));
				list.addRoom(newHouseRoom);
			}
		}
	}
	
	private void setStartRoom(NodeList rooms) {
		for(int i = 0; i < rooms.getLength(); i++) {
			Node room = rooms.item(i);
			if(isAnElementNode(room)) {
				Element roomElement = (Element) room;
				String current = roomElement.getAttribute("start");
				if(current.equals("true")) {
					list.setStartRoom(roomElement.getAttribute("name"));
				}
			}
		}
	}
	
	private void constructExits(NodeList rooms) {
		for (int i = 0; i < rooms.getLength(); i++) {
			Node room = rooms.item(i);
			if(isAnElementNode(room)) {
				Element roomElement = (Element) room;
				NodeList roomExitsList = roomElement.getChildNodes();
				for(int j = 0; j < roomExitsList.getLength(); j++) {
					Node exit = roomExitsList.item(j);
					if(isAnElementNode(exit)) {
						Element exitElement = (Element) exit;
						list.updateRoomsListExits(roomElement.getAttribute("name"), exitElement.getTagName(), exitElement.getTextContent());
					}
				}
			}
		}
	}
	
	private boolean isAnElementNode(Node node) {
		return node.getNodeType() == Node.ELEMENT_NODE;
    }
	
	private boolean verifyTags(Document documentoXml){
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
            return false;
        }
        return true;
    }
	
	private boolean isCorrectTag(String tagName) {
		return (tagName.equals("house") || tagName.equals("room") || tagName.equals("north") ||
				tagName.equals("east") || tagName.equals("south") || tagName.equals("west"));
	}
}
