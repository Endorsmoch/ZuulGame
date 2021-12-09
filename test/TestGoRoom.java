package test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import game.Command;
import game.GameMovement;
import game.MapFileReader;
import game.NullRoom;
import game.Parser;
import game.Room;
import game.RoomList;

public class TestGoRoom {

	MapFileReader reader;
	Command command;
	Parser parser;
	Room beforeRoom;
	Room nextRoom;
	Room currentRoom;
	
	@Before
	public void preconditionGoRoom() {
		beforeRoom = new Room("Sala");
		nextRoom = new Room("Cocina");
		beforeRoom.setExit("east", nextRoom);
		beforeRoom.setExit("north", new NullRoom());
		beforeRoom.setExit("south", new NullRoom());
		beforeRoom.setExit("west", new NullRoom());
		nextRoom.setExit("west", new NullRoom());
		nextRoom.setExit("east", new NullRoom());
		nextRoom.setExit("north", new NullRoom());
		nextRoom.setExit("south", new NullRoom());
		command = new Command("ir", "este");
	}
	@Test
	public void goRoomTestUnos() {
		GameMovement mover = new GameMovement();
		currentRoom = mover.goRoom(command,  beforeRoom);
		//assertEquals(currentRoom, beforeRoom);
	}
	
	@After
	public void postconditionTest() {
		assertEquals(currentRoom.getName(),nextRoom.getName());
	}

}
