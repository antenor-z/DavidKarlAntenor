package Model.Player;

import Model.gameboard.Board;
import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerTest {

	/*
		---------------------------------------------------------------------
		|Player - TestClass - ChangeCash													
		|Testing the player's balance:										
		|Testcases-> Player's balance equal to 4000.									
		|			 Player's balance equal to 3000.
		|			 Player's balance is positive						
		---------------------------------------------------------------------
	*/
	@Test
	public void testChangeCash() {
		Board b = new Board();
		Player p = new Player(4000, b);
		assertEquals("Player's balance needs to be 4000", p.getCash(), 4000);
		try {
		p.changeCash(1000);
		} catch (PlayerException e)
		{
			fail(e.getMessage());
		}
		assertEquals("Player's balance needs to be 3000", p.getCash(), 5000);
		try {
		p.changeCash(-5000);
		} catch (PlayerException e)
		{
			fail(e.getMessage());
		}
		assertEquals("Player's balance needs to be 0", p.getCash(), 0);
		try {
			p.changeCash(-1);
			fail("Fail: 01 -> Player's balance should be postive");
		}catch (PlayerException e) {

		}		
	}

	@Test
	public void testAdvance() {
		Board b = new Board();
		Player p = new Player(4000, b);
		assertTrue("You can advance positive", p.advance(3));
		assertFalse("You can advance positive", p.advance(-3));
		assertTrue("You can advance positive", p.advance(7));
		assertEquals("The game must be at position 10 now", p.getCurrentTile(), 10);
		assertTrue("You can advance positive", p.advance(31));
		assertEquals("The position must roll back to 1", p.getCurrentTile(), 1);
	}
}
