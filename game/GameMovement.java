package game;

import exceptions.SecondParameterException;
import exceptions.IsNotDirectionException;

public class GameMovement {
	
	public Room goRoom(Command command, Room currentRoom) {
		try {
    		if(!command.hasSecondWord()) {
    			throw new SecondParameterException("¿A donde vamos?"); 
            }
     		String direction = command.getSecondWord();
    	       
            Room nextRoom = searchRoom(direction, currentRoom);

            if (nextRoom.isNull()) {
                System.out.println("Ahi no hay una puerta!");
            }
            else {
                currentRoom = nextRoom;
                printCurrentRoomWithExits(currentRoom);
            }
    	}catch(SecondParameterException e) {
    		System.out.println(e.getMessage());
    	}
		return currentRoom;
	}
	
	private Room searchRoom(String direction, Room currentRoom) {
		Room roomFound = new NullRoom();
		try {	
	    	switch(direction) {
	        case "norte":
	        	roomFound = currentRoom.getNorthExit();
	        	break;
	        case "este":
	        	roomFound = currentRoom.getEastExit();
	        	break;
	        case "sur":
	        	roomFound = currentRoom.getSouthExit();
	        	break;
	        case "oeste":
	        	roomFound = currentRoom.getWestExit();
	        	break;
	        default:
	        	throw new IsNotDirectionException("No es una dirección válida");
	        }
		}catch(IsNotDirectionException e) {
			System.out.println(e.getMessage());
		}
		return roomFound;
	}
	
	private void printCurrentRoomWithExits(Room currentRoom) {
    	System.out.println(currentRoom.getDescription());
        System.out.print("Salidas: " + currentRoom.getStringExits() + "\n");
    }
	
}
