
public class Game {
	private Parser parser;

	public Game(){
		parser = new Parser();
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
    	"El mundo de Zull es un nuevo, incre�belmente aburrido juego de aventura.\n" +
        "Escribe 'ayuda' si necesitas ayuda.\n" +
        "\n" +
        "Est�s en ...\n" + //nota: se necesita la habitaci�n actual
        "Salidas: "); //nota: se necesita las salidas de la habitaci�n actual
        
      //nota: Esta funci�n est� incompleta, hace falta las habitaciones
    }

    private boolean processCommand(Command command){
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("No s� a qu� te refieres...");
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
        System.out.println("Est�s perdido. Est�s solo. Deambulas\n" +
        "alrededor de la universidad.\n" +
        "\n" +
        "Tus comandos son:\n" +
        "	ir, salir, ayuda");
    }
    
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("�A donde vamos?");
            return;
        }

        String direction = command.getSecondWord();
       
        //nota: Esta funci�n est� incompleta, hace falta las habitaciones
    }
    
    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("�Salir a donde?");
            return false;
        }
        else {
            return true;
        }
    }
}
