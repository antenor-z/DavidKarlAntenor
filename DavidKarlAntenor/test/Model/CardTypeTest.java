package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTypeTest {

	@Test
	public void test() {
		
		assertTrue("CardType should contain the value 'VALUE'.", Model.CardType.fromInteger(0) == Model.CardType.VALUE);
		assertTrue("CardType should contain the value 'GO_TO_JAIL'.", Model.CardType.fromInteger(1) == Model.CardType.GO_TO_JAIL);
		assertTrue("CardType should contain the value 'OUT_OF_JAIL'.", Model.CardType.fromInteger(2) == Model.CardType.OUT_OF_JAIL);
		assertTrue("CardType should contain the value 'VALUE_PER_PLAYER'.", Model.CardType.fromInteger(3) == Model.CardType.VALUE_PER_PLAYER);
		assertTrue("CardType should contain the value 'MOVE'.", Model.CardType.fromInteger(4) == Model.CardType.MOVE);
		assertNull("CardType should contain the value 'null'.", Model.CardType.fromInteger(5));
	}

}
