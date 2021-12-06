
public class CommandWords {
	private static final String[] validCommands = {"ir", "salir", "ayuda"};

	public boolean isCommand(String aString){
		for(int i = 0; i < validCommands.length; i++) {
			if(validCommands[i].equals(aString))
				return true;
	        }
		
	    return false;
	}
}
