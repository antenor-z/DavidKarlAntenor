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
	public void testChangeCash() throws PlayerException{
		Board b = new Board();
		Player p = new Player(4000, b);
		assertEquals("Player's balance needs to be 4000", p.getCash(), 4000);
		p.changeCash(1000);	
		assertEquals("Player's balance needs to be 3000", p.getCash(), 5000);
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
		Player p = new Player(4000, b);
		assertTrue("You can advance positive", p.advance(3));
		assertFalse("You can advance positive", p.advance(-3));
		assertTrue("You can advance positive", p.advance(7));
		assertEquals("The game must be at position 10 now", p.getCurrentTile(), 10);
		assertTrue("You can advance positive", p.advance(31));
		assertEquals("The position must roll back to 1", p.getCurrentTile(), 1);
	}
	/*
		---------------------------------------------------------------------
		|Player - TestClass - Balance													
		|Testing the player's balance:										
		|Testcases-> Player's balance null.									
		|			 Player's balance is not an integer.						
		---------------------------------------------------------------------
	*/
	@Test
	public void testBalance() {
		
		int balance = 500;
		Board b = new Board();
		Player p = new Player(4000, b);
		balance = p.getCash();
		assertFalse("Player's balance can't be less than 0", balance < 0);
		try {
			Integer.valueOf(balance);
			
		}catch (NumberFormatException e) {
			fail("Fail: 01 -> Player's balance should be an integer value");
		
		}		
		
		
	}
	/*
		---------------------------------------------------------------------
		|Player - TestClass - Territories													
		|Testing the territories owned by player (assuming they will be 	
		|identified by integers):											
		|Testcases-> Player's territory is numbered by 0.								
		|			 Player's territory is numbered by a value less than 0.		
		|			 Player's territory is numbered by a value higher than
		|the amount of territories in he game.	
		---------------------------------------------------------------------
	*/
	@Test
	public void testTerritories() {
		List<Integer> territories = new ArrayList<Integer>();
		territories.add(-1);
		territories.add(-2);
		territories.add(0);
		territories.add(1);
		territories.add(2);
		territories.add(4);
		territories.add(13);
		territories.add(17);
		territories.add(18);
		territories.add(19);
		territories.add(20);
		
		assertFalse("Fail: 02 ->  Territores must not contain the value 0", territories.get(0) == 0);
		assertFalse("Fail: 03 ->  Territores must not contain the value 0", territories.get(1) == 0);
		assertFalse("Fail: 04->  Territores must not contain the value 0", territories.get(2) == 0);
		assertFalse("Fail: 05->  Territores must not contain the value 0", territories.get(3) == 0);
		assertFalse("Fail: 06 ->  Territores must not contain the value 0", territories.get(4) == 0);
		assertFalse("Fail: 07 ->  Territores must not contain the value 0", territories.get(0) < 0);
		assertFalse("Fail: 03 ->  Territores must not contain the value 0", territories.get(1) < 0);
		assertFalse("Fail: 04->  Territores must not contain the value 0", territories.get(2) < 0);
		assertFalse("Fail: 05->  Territores must not contain the value 0", territories.get(3) < 0);
		assertFalse("Fail: 06 ->  Territores must not contain the value 0", territories.get(4) <0);
		assertTrue("Fail: 02 ->  Territores must not contain the value 0", territories.get(5) > 0);
		assertTrue("Fail: 03 ->  Territores must not contain the value 0", territories.get(6) > 0);
		assertTrue("Fail: 04->  Territores must not contain the value 0", territories.get(7) > 0);
		assertTrue("Fail: 05->  Territores must not contain the value 0", territories.get(8) > 0);
		assertFalse("Fail -> Value higher than the amount of territories", territories.get(6) > 18);
		assertFalse("Fail -> Value higher than the amount of territories", territories.get(7) > 18);
		assertFalse("Fail -> Value higher than the amount of territories", territories.get(8) > 18);
		assertFalse("Fail -> Value higher than the amount of territories", territories.get(9) > 18);
		assertFalse("Fail -> Value higher than the amount of territories", territories.get(10) > 18);
		
	}
	/*
		---------------------------------------------------------------------
		|Player - TestClass	- Companies												
		|Testing the territories owned by player (assuming they will be 	
		|identified by integers):											
		|Testcases-> Player's copmany is numbered by 0.								
		|			 Player's company is numbered by a value less than 0.		\
		|			 Player's copmany is numbered by a value higher than
		|the amount of companies in the game.	
		---------------------------------------------------------------------
	*/
	@Test
	public void testCompanies() {
		List<Integer> companies = new ArrayList<Integer>();
		companies.add(-2);
		companies.add(-1);
		companies.add(0);
		companies.add(1);
		companies.add(5);
		companies.add(6);
		companies.add(7);
		assertFalse("Fail: 02 ->  Companies  must not contain the value 0", companies.get(0) == 0);
		assertFalse("Fail: 03 ->  Companies  must not contain the value 0", companies.get(1) == 0);
		assertFalse("Fail: 04->  Companies must not contain the value 0", companies.get(2) == 0);
		assertFalse("Fail: 05->  Companies must not contain the value 0", companies.get(3) == 0);
		assertFalse("Fail: 06 ->  Companies  must not contain the value 0", companies.get(4) == 0);
		assertFalse("Fail: 07 ->  Companies  must not contain the value 0", companies.get(0) < 0);
		assertFalse("Fail: 03 ->  Companies  must not contain the value 0", companies.get(1) < 0);
		assertFalse("Fail: 04->  Companies must not contain the value 0", companies.get(2) < 0);
		assertFalse("Fail: 05->  Companies must not contain the value 0", companies.get(3) < 0);
		assertFalse("Fail: 06 ->  Companies  must not contain the value 0", companies.get(4) <0);
		assertTrue("Fail: 02 ->  Companies must not contain the value 0", companies.get(5) > 0);
		assertTrue("Fail: 03 ->  Companies must not contain the value 0", companies.get(6) > 0);
		assertTrue("Fail: 04->  Companies must not contain the value 0", companies.get(7) > 0);
		assertTrue("Fail: 05->  Companies must not contain the value 0", companies.get(8) > 0);
		assertFalse("Fail -> Value higher than the amount of companies", companies.get(6) > 18);
		assertFalse("Fail -> Value higher than the amount of companies", companies.get(7) > 18);
		assertFalse("Fail -> Value higher than the amount of companies", companies.get(8) > 18);
		
	}
	/*
		---------------------------------------------------------------------
		|Player - TestClass	- Deposit												
		|Testing the territories owned by player (assuming they will be 	
		|identified by integers):											
		|Testcases-> Player's deposit contains value 0.								
		|			 Player's deposit contains value less than 0.	
		|			 Player's deposit contains value NULL.		
		|			
		---------------------------------------------------------------------
	*/
	@Test
	public void testDeposit(){
		int amount = 200;
		assertFalse("Fail: 26 -> Deposits cannot contain the value 0", amount == 0);
		assertFalse("Fail: 28 -> Deposits cannot contain values less than 0", amount < 0);
        try {
			Integer.valueOf(amount);
			
		}catch (NumberFormatException e) {
			fail("Fail: 01 -> Player's deposit value should be an integer value");
		
		}
	}
	/*
		---------------------------------------------------------------------
		|Player - TestClass	- Deposit												
		|Testing the territories owned by player (assuming they will be 	
		|identified by integers):											
		|Testcases-> Player's withdrawn contains value 0.		
		|			 Player's withdrawn contains value NULL.						
		|			 Player's withdrawn contains value less than 0.	
		|			 		
		|			
		---------------------------------------------------------------------
	*/
	@Test
	public void testWithdraw(){
		int amount = 300;
		assertFalse("Fail: 29 -> Withdrawns cannot contain the value 0", amount == 0);
		assertFalse("Fail: 31 -> Withdrawns cannot contain values less than 0", amount < 0);
        try {
			Integer.valueOf(amount);
			
		}catch (NumberFormatException e) {
			fail("Fail: 01 -> Withdrawn amount should be an integer value.");
		
		}
	}
	
}
