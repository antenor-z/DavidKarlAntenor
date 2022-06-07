package Model;

import static org.junit.Assert.*;

import org.junit.Test;
import Model.FreeStop;

import java.util.ArrayList;

public class FreeStopTest {

	@Test
	public void test() {

		FreeStop freestop = new FreeStop();
		assertEquals("Free stop must return the default value." , freestop, "I'm a freestop tile");
        assertTrue("Current tile should be an instance of FreeStop type.", freestop instanceof FreeStop);
        ArrayList<Player> players = new ArrayList<Player>();
		Board board = new Board(players);
        assertTrue("This tile should be an instance of FreeStop", board.getTile(20) instanceof FreeStop);
        assertFalse("This tile should not be an instance of FreeStop", board.getTile(21)instanceof FreeStop);
        
	}

}
