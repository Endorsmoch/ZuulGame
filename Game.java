
public class Game {
	private Parser parser;
	private Room currentRoom;

	public Game(){
		parser = new Parser();
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
    	"El mundo de Zull es un nuevo, increíbelmente aburrido juego de aventura.\n" +
        "Escribe 'ayuda' si necesitas ayuda.\n" +
        "\n" +
        "Estás en " + currentRoom.getName() +"\n" + 
        "Salidas: " + currentRoom.getStringExits()); 
    }

    private boolean processCommand(Command command){
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("No sé a qué te refieres...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("ayuda"))
            printHelp();
        else if (commandWord.equals("ir"))
            goRoom(command);
        else if (commandWord.equals("salir"))
            wantToQuit = quit(command);

        return wantToQuit;
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
        if(!command.hasSecondWord()) {
            System.out.println("¿A donde vamos?");
            return;
        }

        String direction = command.getSecondWord();
       
        Room nextRoom = null;
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

        if (nextRoom == null) {
            System.out.println("Ahi no hay una puerta!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getDescription());
            System.out.print("Salidas: " + currentRoom.getStringExits() + "\n");
        }
    }
    
    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("¿Salir a donde?");
            return false;
        }
        else {
            return true;
        }
    }
}
