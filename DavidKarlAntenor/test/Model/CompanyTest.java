package Model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CompanyTest {

	@Test
	public void testBuyCompany() throws CompanyException, PlayerException {
	    ArrayList<Player> players = new ArrayList<Player>();
		Board b = new Board(players);
		Player blue = new Player(4000, PlayerColor.Blue, "blue");
		Player orange = new Player(4000, PlayerColor.Orange, "orange");
		assertTrue("Position 5 not company", b.getTile(5) instanceof Company);
		Company c = (Company)b.getTile(5);
		c.buyCompany(blue);
		assertEquals("Blue should be the owner", c.getOwner(), blue);
		try
		{
			c.buyCompany(orange);
			fail("Company is owned by blue");
		}
		catch(CompanyException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void testPayRent() throws CompanyException, PlayerException {
		ArrayList<Player> players = new ArrayList<Player>();
		Board b = new Board(players);
		Player blue = new Player(4000, PlayerColor.Blue, "blue");
		Player orange = new Player(4000, PlayerColor.Orange, "orange");
		Company c = (Company)b.getTile(5);
		c.buyCompany(orange);
		c.payRent(blue, 4);
		assertEquals("orange should have received rent", c.getOwner().getCash(), 4000+200);
		assertEquals("blue should have payed rent", blue.getCash(), 4000-200);
		try
		{
			c.payRent(c.getOwner(), 4);
			fail("Can't pay rent to themself.");
		}
		catch(CompanyException e)
		{
			System.out.println(e.getMessage());
		}
		Company d = (Company)b.getTile(15);
		d.buyCompany(blue);
		d.payRent(orange, 6);
		assertEquals("blue should have received rent", d.getOwner().getCash(), 3800 + 240);
		assertEquals("orange should have payed rent", orange.getCash(), 4200 - 240);
	}

}
