public class Room {
	
	private String name;
	private String description;
	private Room northExit;
	private Room southExit;
	private Room eastExit;
	private Room westExit;
	
	public Room() {
		
	}
	
	public Room(String name) {
		this.name = name;
		this.description = "Tu estas en: " + name;
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
	
	public String getStringExits() {
		String exits = "";
		if(!northExit.isNull()) {
            exits += "norte ";
        }
        if(!eastExit.isNull()) {
        	 exits += "este ";
        }
        if(!southExit.isNull()) {
        	 exits += "sur ";
        }
        if(!westExit.isNull()) {
        	 exits += "oeste ";
        }
        return exits;
	}
	
	public Room getNorthExit() {
		return northExit;
	}

	public Room getSouthExit() {
		return southExit;
	}

	public Room getEastExit() {
		return eastExit;
	}

	public Room getWestExit() {
		return westExit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public boolean isNull() {
		return false;
	}
	
}
