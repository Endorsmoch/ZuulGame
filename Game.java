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
		file.readFile();
		currentRoom =  RoomList.getInstance().getStartRoom();
	}
	
	public void play(){
		printWelcome();
		
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Gracias por jugar.\nAdios.");
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
             if (commandWord.equals("ayuda"))
                 printHelp();
             else if (commandWord.equals("ir"))
                 goRoom(command);
             else if (commandWord.equals("salir"))
                 wantToQuit = quit(command);

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
            if(direction.equals("norte")) {
                nextRoom = currentRoom.getNorthExit();
            }
            if(direction.equals("este")) {
                nextRoom = currentRoom.getEastExit();
            }
            if(direction.equals("sur")) {
                nextRoom = currentRoom.getSouthExit();
            }
            if(direction.equals("oeste")) {
                nextRoom = currentRoom.getWestExit();
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
    
    private boolean quit(Command command) {
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
