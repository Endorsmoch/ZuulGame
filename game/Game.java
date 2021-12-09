package game;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.IsNotCommandException;
import exceptions.SecondParameterException;
public class Game {
	private Parser parser;
	private Room currentRoom;

	public Game(){
		parser = new Parser();
		initConfig();
	}
	
	private void initConfig() {
		MapFileReader file = new MapFileReader();
		if(file.readFile()) {
			currentRoom =  RoomList.getInstance().getStartRoom();
		}
	}
	
	public void play(){
		if(currentRoom != null) {
			printWelcome();
			boolean finished = false;
	        while (! finished) {
	            Command command = parser.getCommand();
	            finished = processCommand(command);
	        }
	        System.out.println("Gracias por jugar.\nAdios.");
		}else {
			System.out.println("El archivo es incorrecto.\nAdios.");
		}
        
	}
	
    private void printWelcome(){
        System.out.println("!Bienvenido al mundo de Zull!\n" +
    	"El mundo de Zull es un nuevo juego de aventura.\n" +
        "Escribe 'ayuda' si necesitas ayuda.\n" +
        "\n" +
        "Estás en " + currentRoom.getName() +"\n" + 
        "Salidas: " + currentRoom.getStringExits()); 
    }

    private boolean processCommand(Command command){
        boolean wantToQuit = false;

        try {
        	if(command.isUnknown()) {
        		throw new IsNotCommandException("Comando incorrecto"); 
            }
        	 String commandWord = command.getCommandWord();
        	 switch(commandWord) {
        	 case "ayuda":
        		 printHelp();
        		 break;
        	 case "ir":
        		 goRoom(command);
        		 break;
        	 case "salir":
        		 wantToQuit = quit(command);
        		 break;
        	 }
             return wantToQuit;
        }catch(IsNotCommandException e){
        	System.out.println(e.getMessage());
        	 return false;
        }
    }
    
    private void printHelp() {
        System.out.println("Estás perdido. Estás solo. Deambulas\n" +
        "alrededor de la universidad.\n" +
        "\n" +
        "Tus comandos son:\n" +
        "	ir, salir, ayuda");
    }
    
    private void goRoom(Command command) 
    {
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

    }
    
    private boolean quit(Command command) throws SecondParameterException{
    	try {
    		 if(command.hasSecondWord()) {
    			 throw new SecondParameterException("¿Salir a donde?");  
    	     }
    		 return true;
    	}catch(SecondParameterException e) {
    		System.out.println(e.getMessage());
    		return false;
    	}
    }
}
