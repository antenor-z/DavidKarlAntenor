import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Test;
import Model.PlayerTest;
public class playerTest {

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
		assertNotNull(balance, "Fail: 00 -> Player's balance cannot be null");
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
		|			 Player's territory is numbered by a value less than 0.		\
		|			 Player's territory is numbered by a value higher than
		|the amount of territories inthe game.	
		---------------------------------------------------------------------

	*/
	@Test
	public void testTerritories() {
		List<Integer> territories = new ArrayList<Integer>();
		territories.add(0);
		territories.add(1);
		territories.add(2);
		territories.add(4);
		territories.add(13);
		territories.add(17);
		territories.add(18);
		territories.add(19);
		territories.add(20);
		
		assertNotSame(0, territories.get(0), "Fail: 02 -> Territores must not contain the value 0");
		assertNotSame(0, territories.get(1), "Fail: 03 -> Territores must not contain the value 0");
		assertNotSame(0, territories.get(2), "Fail: 04 -> Territores must not contain the value 0");
		assertNotSame(0, territories.get(3), "Fail: 06 -> Territores must not contain the value 0");
		assertFalse(territories.get(0) < 0, "Fail: 07 -> Territories must not contain values less than 0");
		assertFalse(territories.get(1) < 0, "Fail: 08 -> Territories must not contain values less than 0");
		assertFalse(territories.get(2) < 0, "Fail: 09 -> Territories must not contain values less than 0");
		assertFalse(territories.get(3) < 0, "Fail: 10 -> Territories must not contain values less than 0");	
		assertFalse(territories.get(4) > 18, "Fail: 11 -> There are no more than 18 territories in the game");
		assertFalse(territories.get(5) > 18, "Fail: 12 -> There are no more than 18 territories in the game");
		assertFalse(territories.get(7) > 18, "Fail: 13 -> There are no more than 18 territories in the game");
		assertFalse(territories.get(8) > 18, "Fail: 14 -> There are no more than 18 territories in the game");
		
		
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
		territories.add(0);
		territories.add(1);
		territories.add(5);
		territories.add(6);
		assertNotSame(0, companies.get(0), "Fail: 15 -> Companies must not contain the value 0");
		assertNotSame(0, companies.get(1), "Fail: 16 -> Companies must not contain the value 0");
		assertNotSame(0, companies.get(2), "Fail: 17 -> Companies must not contain the value 0");
		assertNotSame(0, companies.get(3), "Fail: -> Companies must not contain the value 0");
		assertFalse(companies.get(0) < 0, "Fail: 18 -> Companies must not contain values less than 0");
		assertFalse(companies.get(1) < 0,"Fail: 19 -> Companies must not contain values less than 0");
		assertFalse(companies.get(2) < 0, "Fail: 20 -> Companies must not contain values less than 0");
		assertFalse(companies.get(3) < 0, "Fail: 21 -> Companies must not contain values less than 0");
		assertFalse(companies.get(0) > 5, "Fail: 22 -> There are no more than 5 companies in the game");
		assertFalse(companies.get(1) > 5, "Fail: 23 -> There are no more than 5 companies in the game");
		assertFalse(companies.get(2) > 5, "Fail: 24 -> There are no more than 5 companies in the game");
		assertFalse(companies.get(3) > 5, "Fail: 25 -> There are no more than 5 companies in the game");
		
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
		assertNotEqual(0, amount, "Fail: 26 -> Deposits cannot contain the value 0");
		assertNotNull(amount, "Fail: 27 -> Deposits cannot contain the value NULL");
		assertFalse(amount < 0, "Fail: 28 -> Deposits cannot contain values less than 0");

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
		assertNotEqual(0, amount, "Fail: 29 -> Withdrawns cannot contain the value 0");
		assertNotNull(amount, "Fail: 30 -> Withdrawns cannot contain the value NULL");
		assertFalse(amount < 0, "Fail: 31 -> Withdrawns cannot contain values less than 0");
	}



}
