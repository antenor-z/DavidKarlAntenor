package Model.Player;

import Model.GameSettings;
import Model.gameboard.Board;
import Model.GameSettings;
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
		/*
		---------------------------------------------------------------------
		|Player - TestClass - Balance													
		|Testing the player's balance:										
		|								
		|			 Player's balance is not an integer.						
		---------------------------------------------------------------------
	*/
	@Test
	public void testChangeCash() throws PlayerException{
		Board b = new Board();
		int balance = GameSettings.getInstance().getStartingBalance();
		Player p = new Player(balance, b, PlayerColor.RED);
		try {
			Integer.valueOf(p.getCash());
			
		}catch (NumberFormatException e) {
			fail("Fail: -> Player's balance should be an integer value");
		
		}	
		assertEquals("Player's balance needs to be 4000", p.getCash(), 4000);
		p.changeCash(1000);	
		assertEquals("Player's balance needs to be 5000", p.getCash(), 5000);
		p.changeCash(-5000);
		assertEquals("Player's balance needs to be 0", p.getCash(), 0);	
		try
		{
			p.changeCash(-1);
			fail("Player balance cannot be less than zero");
		}
		catch(PlayerException e)
		{
			
		}
		
	}	
	
	@Test
	public void testAdvance() {
		Board b = new Board();
		Player p = new Player(4000, b, PlayerColor.PURPLE);
		assertTrue("You can advance positive", p.goFoward(3));
		assertEquals("Position 3", p.getCurrentTile(), 3);
		
		assertFalse("You can't advance positive", p.goFoward(-3));
		assertEquals("Position 3", p.getCurrentTile(), 3);
		
		assertTrue("You can advance positive", p.goFoward(7));
		assertEquals("The game must be at position 10 now", p.getCurrentTile(), 10);
		
		assertTrue("You can advance positive", p.goFoward(31));
		assertEquals("The position must roll back to 1", p.getCurrentTile(), 1);
		
		p.goToTile(19);
		assertEquals("Position 19", p.getCurrentTile(), 19);
	}

}
