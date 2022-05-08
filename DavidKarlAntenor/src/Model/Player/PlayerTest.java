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
	
	/*
<<<<<<< HEAD
	---------------------------------------------------------------------
	|Player - TestClass - Advance													
	|Testing the advance method:										
	|Testcases-> Player can move +3.									
	|			 Player cannot move -3.
	|			 Player can move +7
	|		     Player is in position 10 now
	|		     Player can move +31
	|			 Player is in position 1					
	---------------------------------------------------------------------
*/
=======
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
>>>>>>> refs/remotes/origin/main
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
<<<<<<< HEAD
	
=======
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


>>>>>>> refs/remotes/origin/main

}
