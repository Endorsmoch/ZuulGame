package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import game.NullRoom;
import game.Room;

class GameTest {
	
	Room roomA;
	Room roomB;
	Room roomC;
	Room roomD;
	Room roomE;
	
	@Before
	void before() {
		roomA = new Room("RoomA");
		roomB = new Room("RoomB");
		roomC = new Room("RoomC");
		roomD = new Room("RoomD");
		roomE = new NullRoom();
		
		roomA.setExit("north", roomB);
		roomA.setExit("east", roomC);
		roomA.setExit("west", roomD);
		roomA.setExit("south", roomE);
	}
	
	@Test
	void roomTest() {
		assertEquals(true, roomE.isNull());
	}

}
