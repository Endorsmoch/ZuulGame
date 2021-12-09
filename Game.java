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
        	 switch(commandWord) {
        	 case "ayuda":
        		 printHelp();
        		 break;
        	 case "ir":
        		 goRoom(command, currentRoom);
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
    
    private void goRoom(Command command, Room actualRoom)
    {
    	GameMovement mover =  new GameMovement();
    	currentRoom = mover.goRoom(command, actualRoom);
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
