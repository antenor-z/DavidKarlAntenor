package Model;
import Model.Dice;
import static org.junit.Assert.*;

import org.junit.Test;
public class DiceTest {
	@Test
	public void testRoll() {
		 int[] dicesPreset = new int[2];
		 dicesPreset[0] = dicesPreset[1] = -1;
		assertTrue("Must be less than 6", Dice.roll(dicesPreset)[0] < 6); 
		assertTrue("Must be less than 6", Dice.roll(dicesPreset)[0] < 6); 
		assertTrue("Must be less than 6", Dice.roll(dicesPreset)[0] < 6); 
		assertTrue("Must be less than 6", Dice.roll(dicesPreset)[1] < 6); 
		assertTrue("Must be less than 6", Dice.roll(dicesPreset)[1] < 6); 
		assertTrue("Must be less than 6", Dice.roll(dicesPreset)[1] < 6); 
	}
}
