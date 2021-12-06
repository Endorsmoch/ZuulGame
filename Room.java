public class Room {
	
	public String name;
	public String description;
	public Room northExit;
	public Room southExit;
	public Room eastExit;
	public Room westExit;
	
	public Room() {
		
	}
	
	public Room(String name) {
		this.name = name;
		this.description = "You are in the: " + name;
	}
	
	public void setExit(String exit, Room exitRoom) {
		switch(exit) {
		case "north":
			this.northExit = exitRoom;
			break;
		case "south":
			this.southExit = exitRoom;
			break;
		case "east":
			this.eastExit = exitRoom;
			break;
		case "west":
			this.westExit = exitRoom;
			break;
		default:
			throw new RuntimeException("There is not that location option for an exit");
		}
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	}
	
}
