package Model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Model.Player.Player;
import Model.gameboard.Board;
import Model.gameboard.Company;
import Model.gameboard.FreeStop;
import Model.gameboard.GoToPrision;
import Model.gameboard.Land;
import Model.gameboard.LuckSetback;
import Model.gameboard.Money;
import Model.gameboard.Prision;
import Model.gameboard.Start;

public class BoardTest {

	@Test
	public void test() {
		ArrayList<Player> players = new ArrayList<Player>();
		Board board = new Board(players);
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
