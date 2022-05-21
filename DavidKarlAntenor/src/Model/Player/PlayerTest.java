package Model.Player;

import Model.GameSettings;
import Model.PlayerColor;
import Model.gameboard.Board;
import Model.GameSettings;
import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerTest {
	@Test
	public void testChangeCash() throws PlayerException{
		int balance = GameSettings.getInstance().getStartingBalance();
		Player p = new Player(balance, 40, PlayerColor.Red);
		try {
			Integer.valueOf(p.getCash());
			
		}catch (NumberFormatException e) {
			fail("Fail: -> Player's balance should be an integer value");
		
		}	
		assertEquals("Player's balance needs to be 4000", p.getCash(), 4000);
		p.addOrSubCash(1000);	
		assertEquals("Player's balance needs to be 5000", p.getCash(), 5000);
		p.addOrSubCash(-5000);
		assertEquals("Player's balance needs to be 0", p.getCash(), 0);	
		try
		{
			p.addOrSubCash(-1);
			fail("Player balance cannot be less than zero");
		}
		catch(PlayerException e)
		{
			
		}
		p.addOrSubCash(5000);
		assertEquals("Player's balance needs to be 5000", p.getCash(), 5000);	
		p.addOrSubCash(-100);
		assertEquals("Player's balance needs to be 4900", p.getCash(), 4900);
	}	
	
	@Test
	public void testAdvance() {
		Board b = new Board();
		Player p = new Player(4000, 40, PlayerColor.Purple);
		try
		{
			p.goFoward(3);
		} catch(PlayerException p1) {fail("You should be able to advance 3");};
		assertEquals("Position 3", p.getCurrentTile(), 3);
		try
		{
			p.goFoward(3);
			
		} catch(PlayerException p2) {fail("You should be able to advance 3");};
		assertEquals("Position 6", p.getCurrentTile(), 6);
		try
		{
			p.goFoward(-3);
			fail("You cannot go back 3");
		} catch(PlayerException p2) {};
		assertEquals("Position 6", p.getCurrentTile(), 6);
		try
		{
			p.goFoward(41);
		} catch(PlayerException p2) {fail("We should be at position 7");};
		assertEquals("Position 7", p.getCurrentTile(), 7);		
	}
}
