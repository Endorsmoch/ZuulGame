package test;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.TagNameException;
import game.Game;

public class ZuulGameTest {
	
	/*
	@Test(expected = TagNameException.class)
	public void wrongTag() {
		Game game = new Game();
		game.play();
	}*/

	@Test(expected = NullPointerException.class)
	public void whenExceptionThrown_thenExpectationSatisfied() {
	    String test = null;
	    test.length();
	}
}
