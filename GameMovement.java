import exceptions.SecondParameterException;

public class GameMovement {
	
	public Room goRoom(Command command, Room currentRoom) {
		try {
    		if(!command.hasSecondWord()) {
    			throw new SecondParameterException("¿A donde vamos?"); 
            }
     		String direction = command.getSecondWord();
    	       
            Room nextRoom = new NullRoom();
            switch(direction) {
            case "norte":
            	nextRoom = currentRoom.getNorthExit();
            	break;
            case "este":
            	nextRoom = currentRoom.getEastExit();
            	break;
            case "sur":
            	nextRoom = currentRoom.getSouthExit();
            	break;
            case "oeste":
            	nextRoom = currentRoom.getWestExit();
            	break;
            }

            if (nextRoom.isNull()) {
                System.out.println("Ahi no hay una puerta!");
            }
            else {
                currentRoom = nextRoom;
                System.out.println(currentRoom.getDescription());
                System.out.print("Salidas: " + currentRoom.getStringExits() + "\n");
            }
    	}catch(SecondParameterException e) {
    		System.out.println(e.getMessage());
    	}
		return currentRoom;
	}
}
