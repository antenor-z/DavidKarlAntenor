package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {

	@Test
	public void testRoll() {
		assertTrue("Must be less than 6", Dice.roll()[0] < 6); 
		assertTrue("Must be less than 6", Dice.roll()[0] < 6); 
		assertTrue("Must be less than 6", Dice.roll()[0] < 6); 
		assertTrue("Must be less than 6", Dice.roll()[1] < 6); 
		assertTrue("Must be less than 6", Dice.roll()[1] < 6); 
		assertTrue("Must be less than 6", Dice.roll()[1] < 6); 
	}

}
