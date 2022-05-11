package Model.gameboard;

import static org.junit.Assert.*;
import org.junit.Test;

import Model.Player.Player;
import Model.Player.PlayerColor;
import Model.Player.PlayerException;

public class LandTest{
  @Test
	public void testBuyLand() throws LandException {
		Board b = new Board();
		Player p = new Player(4000, b, PlayerColor.BLUE);
		Player p1 = new Player(4000, b, PlayerColor.ORANGE);
		assertTrue("Position 6 not buyable land", b.getTile(6) instanceof Land);
		Land l = (Land)b.getTile(6);
		l.buyLand(p);
		try {
			l.buyLand(p1);
			fail("Fail -> Land already owned by other player.");
		}
		catch(LandException e)
		{
			System.out.print(e.getMessage());
		}
	}
  @Test 
	public void testRent() throws LandException, PlayerException {
		Board b = new Board();
		Player p = new Player(4000, b, PlayerColor.BLUE);
		Player p1 = new Player(4000, b, PlayerColor.ORANGE);
		Land l = (Land) b.getTile(6);//Rent = 20 - 1 House = 100 - 2 House = 300 - 3 House = 750 - 4 House = 925 - Hotel = 1100
		Land l1 = (Land) b.getTile(1);//Rent = 6 - 1 House = 30 - 2 House = 90 - 3 House = 270 - 4 House = 400 - Hotel = 500
		try {
			
			l.payRent(p);
			fail("This land has no owner yet");
		}	
		catch(LandException e)
		{
			System.out.print(e.getMessage());
		}
		l.buyLand(p);
		l1.buyLand(p1);
		try {
			l.payRent(p);
			fail("This land is owned by the player trying to pay the rent");
		}
		catch(LandException e){
			System.out.print(e.getMessage());
		}
		try {
			l1.payRent(p1);
			fail("This land is owned by the player trying to pay the rent");
		}
		catch(LandException e){
			System.out.print(e.getMessage());
		}
		int p1Cash = 4000 - 20;
		int pCash = 4000 + 20;
		l.payRent(p1);
		assertEquals("Player p1 should have paid rent.", p1.getCash(), p1Cash);
		assertEquals("Player p should have received rent.", p.getCash(), pCash);
		l.buildHouse(p);
		pCash -= 150;
		l.payRent(p1);
		pCash += 100;
		p1Cash -= 100;
		assertEquals("Player p1 should have paid rent.", p1.getCash(), p1Cash);
		assertEquals("Player p should have received rent.", p.getCash(), pCash);
		l.buildHouse(p);
		pCash -= 150;
		l.payRent(p1);
		pCash += 300;
		p1Cash -= 300;
		assertEquals("Player p1 should have paid rent.", p1.getCash(), p1Cash);
		assertEquals("Player p should have received rent.", p.getCash(), pCash);
		l.buildHouse(p);
		pCash -= 150;
		l.payRent(p1);
		pCash += 750;
		p1Cash -= 750;
		assertEquals("Player p1 should have paid rent.", p1.getCash(), p1Cash);
		assertEquals("Player p should have received rent.", p.getCash(), pCash);
		l.buildHouse(p);
		pCash -= 150;
		l.payRent(p1);
		pCash += 925;
		p1Cash -= 925;
		assertEquals("Player p1 should have paid rent.", p1.getCash(), p1Cash);
		assertEquals("Player p should have received rent.", p.getCash(), pCash);
		l.buildHotel(p);
		pCash -= 150;
		l.payRent(p1);
		pCash += 1100;
		p1Cash -= 1100;
		assertEquals("Player p1 should have paid rent.", p1.getCash(), p1Cash);
		assertEquals("Player p should have received rent.", p.getCash(), pCash);
		
	}		
}
