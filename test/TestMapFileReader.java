package test;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import game.MapFileReader;

public class TestMapFileReader {
	
	MapFileReader reader;
	
	@Before
	public void beforeTest(){
		reader = new MapFileReader();
	}
	
	@Test
	public void test() {
		assertTrue(reader.readFile());
	}
}
