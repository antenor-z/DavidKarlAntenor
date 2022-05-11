package Model.gameboard;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void test() {
		Board board = new Board();
		board.printAllTiles();
		assertTrue("0 != Start", board.getTile(0) instanceof Start);
		assertTrue("1 != Land", board.getTile(1) instanceof Land);
		assertTrue("2 != LuckSetBack", board.getTile(2) instanceof LuckSetback);
		assertTrue("3 != LuckSetBack", board.getTile(3) instanceof Land);
		assertTrue("5 != LuckSetBack", board.getTile(5) instanceof Company);
		assertTrue("10 != Prision", board.getTile(10) instanceof Prision);
		assertTrue("18 != Money", board.getTile(18) instanceof Money);
		assertTrue("20 != Money", board.getTile(20) instanceof FreeStop);
		assertTrue("30 != GoToPrision", board.getTile(30) instanceof GoToPrision);
	}

}
