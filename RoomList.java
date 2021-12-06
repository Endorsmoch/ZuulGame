import java.util.ArrayList;


public class RoomList {

	private ArrayList<Room> roomsList = new ArrayList<Room>();
	private static RoomList instance;
	
	private RoomList() {
		
	}
	public static RoomList getInstance() {
		if(instance == null) {
			instance = new RoomList();
		}
		return instance;
	}
	
	public ArrayList<Room> getRoomsList(){
		return roomsList;
	}
	
	public void addRoom(Room newRoom) {
		try{
			roomsList.add(newRoom);
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		
	}
	
	private Room searchRoom(String roomName){
		Room returnRoom = new Room();
		System.out.println("Hey: "+roomName);
		if(!roomName.equals("Nothing")) {
			for(int i = 0; i < roomsList.size(); i++) {
				if(roomName.equals(roomsList.get(i).getName())) {
					returnRoom = roomsList.get(i);
				}
			}
			System.out.println("My return Room is : "+returnRoom.getName());
			return returnRoom;
		}else {
			return null;
		}	
	}
	
	public void updateRoomsListExits(String roomToUpdate, String exit, String nameExitRoom) {
		Room roomChanging = searchRoom(roomToUpdate);
		Room exitRoom = searchRoom(nameExitRoom);
		roomChanging.setExit(exit, exitRoom);
		
	}
}
