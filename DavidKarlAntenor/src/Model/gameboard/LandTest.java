package Model.gameboard;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class LandTest{
  @Test
	public void testBuyLand() {
		Board b = new Board();
		Player p = new Player(4000, b);
		Player p1 = new Player(4000, b);
		Land l = b.getTile(6);
		assertTrue("Position 6 not buyable land", l instanceof Company);
		l.buyLand(p);
		try {
			l.buyLand(p1);
			fail("Fail -> Land already owned by other player.")
		}
		catch(LandException e)
		{
			System.out.print(e.getMessage());
		}
	}
  @Test 
	public void testRent() {
		Board b = new Board();
		Player p = new Player(4000, b);
		Player p1 = new Player(4000, b);
		Land l = b.getTile(6);//Rent = 20 - 1 House = 100 - 2 House = 300 - 3 House = 750 - 4 House = 925 - Hotel = 1100
		Land l1 = b.getTile(1);//Rent = 6 - 1 House = 30 - 2 House = 90 - 3 House = 270 - 4 House = 400 - Hotel = 500
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
		l.payRent(p1);
		assertEquals("Player p1 should have paid rent.", p1.getCash(), 4000 - 20);
		assertEquals("Player p should have received rent.", p.getCash(), 4000 + 20);
		l.buildHouse(p);
		l.payRent(p1);
		assertEquals("Player p1 should have paid rent.", p1.getCash(), (4000 - 20) - 100);
		assertEquals("Player p should have received rent.", p.getCash(),(4000 + 20) + 100);
		l.buildHose(p);
		l.payRent(p1);
		assertEquals("Player p1 should have paid rent.", p1.getCash(), (4000 - 20 - 100) - 300);
		assertEquals("Player p should have received rent.", p.getCash(),(4000 + 20 + 100) + 300);
		l.buildHose(p);
		l.payRent(p1);
		assertEquals("Player p1 should have paid rent.", p1.getCash(), (4000 - 20 - 100 - 300) - 750);
		assertEquals("Player p should have received rent.", p.getCash(),(4000 + 20 + 100 + 300) + 750);
		l.buildHouse(p);
		l.payRent(p1);
		assertEquals("Player p1 should have paid rent.", p1.getCash(), (4000 - 20 - 100 - 300 - 750) - 925);
		assertEquals("Player p should have received rent.", p.getCash(),(4000 + 20 + 100 + 300 +750) + 925);
		l.buildHotel(p);
		l.payRent(p1);
		assertEquals("Player p1 should have paid rent.", p1.getCash(), (4000 - 20 - 100 - 300 - 750 - 925) - 1100);
		assertEquals("Player p should have received rent.", p.getCash(),(4000 + 20 + 100 + 300 +750 + 925) + 1100);
		
		
	}		
}
