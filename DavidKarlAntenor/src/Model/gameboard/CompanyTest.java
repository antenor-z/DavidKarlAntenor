package Model.gameboard;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Player.Player;
import Model.Player.PlayerColor;

public class CompanyTest {

	@Test
	public void testBuyCompany() throws CompanyException {
		Board b = new Board();
		Player p = new Player(4000, b, PlayerColor.BLUE);
		Player q = new Player(4000, b, PlayerColor.ORANGE);
		assertTrue("Position 5 not company", b.getTile(5) instanceof Company);
		Company c = (Company)b.getTile(5);
		c.buyCompany(p);
		try
		{
			c.buyCompany(q);
			fail("Company is owned by p");
		}
		catch(CompanyException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void testPayRent() throws CompanyException {
		Board b = new Board();
		Player p = new Player(4000, b, PlayerColor.BLUE);
		Player q = new Player(4000, b, PlayerColor.ORANGE);
		Company c = (Company)b.getTile(5);
		c.buyCompany(p);
		c.payRent(q, 4);
		assertEquals("p should have received rent", p.getCash(), 4000+200);
		assertEquals("q should have payed rent", q.getCash(), 4000-200);
		
	}

}
