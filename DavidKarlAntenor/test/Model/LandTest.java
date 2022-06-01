package Model;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Model.Player.Player;
import Model.Player.PlayerException;
import Model.gameboard.Board;
import Model.gameboard.Land;
import Model.gameboard.LandException;

public class LandTest{
  @Test
	public void testBuyLand() throws LandException, PlayerException {
	  	ArrayList<Player> players = new ArrayList<Player>();
		Board b = new Board(players);
		Player blue = new Player(4000, 40, PlayerColor.Blue, "blue");
		Player orange = new Player(4000, 40, PlayerColor.Orange, "orange");
		assertTrue("Position 6 not buyable land", b.getTile(6) instanceof Land);
		Land land = (Land)b.getTile(6);
		land.buyLand(blue);
		assertEquals("land should be owned by blue", land.getOwner(), blue);
		try {
			land.buyLand(orange);
			fail("Fail -> Land already owned by other player.");
		}
		catch(LandException e)
		{
			System.out.print(e.getMessage());
		}
	}
  @Test 
	public void testRent() throws LandException, PlayerException {
	  	ArrayList<Player> players = new ArrayList<Player>();
		Board b = new Board(players);
		Player blue = new Player(4000, 40, PlayerColor.Blue, "blue");
		Player orange = new Player(4000, 40, PlayerColor.Orange, "orange");
		Land fariaLima = (Land) b.getTile(6);//Rent = 20 - 1 House = 100 - 2 House = 300 - 3 House = 750 - 4 House = 925 - Hotel = 1100
		Land leblon = (Land) b.getTile(1);//Rent = 6 - 1 House = 30 - 2 House = 90 - 3 House = 270 - 4 House = 400 - Hotel = 500
		try {	
			leblon.payRent(orange);
			fail("This land has no owner yet");
		}	
		catch(LandException e)
		{
			System.out.print(e.getMessage());
		}
		leblon.buyLand(blue);
		fariaLima.buyLand(orange);
		try {
			leblon.payRent(blue);
			fail("This land is owned by the player trying to pay the rent");
		}
		catch(LandException e){
			System.out.print(e.getMessage());
		}
		try {
			fariaLima.payRent(orange);
			fail("This land is owned by the player trying to pay the rent");
		}
		catch(LandException e){
			System.out.print(e.getMessage());
		}
		int orangeCash = 4000 - 6;
		int blueCash = 4000 + 6;	
		leblon.payRent(orange);
		assertEquals("Player orange should have paid rent.", orange.getCash(), orangeCash);
		assertEquals("Player blue should have received rent.", blue.getCash(), blueCash);
		
		int price[] = {30, 90, 270, 400};
		for(int i = 0; i < 3; i++)
		{
			leblon.buildHouse();
			blueCash -= 50;
			leblon.payRent(orange);
			orangeCash -= price[i];
			blueCash += price[i];
			assertEquals("Player orange should have paid rent.", orange.getCash(), orangeCash);
			assertEquals("Player blue should have received rent.", blue.getCash(), blueCash);
		}
		
		leblon.buildHotel();
		blueCash -= 50;
		leblon.payRent(orange);
		orangeCash -= 500;
		blueCash += 500;
		assertEquals("Player orange should have paid rent.", orange.getCash(), orangeCash);
		assertEquals("Player blue should have received rent.", blue.getCash(), blueCash);
	}		
}
