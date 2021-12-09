package test;


import static org.junit.Assert.*;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import game.MapFileReader;

public class ZuulGameTest {
	
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
