package Model.gameboard;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Player.Player;

public class CompanyTest {

	@Test
	public void testBuyCompany() throws CompanyException {
		Board b = new Board();
		Player p = new Player(4000, b);
		Player q = new Player(4000, b);
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

}
