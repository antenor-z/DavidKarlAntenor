package Model.Deck.Tests;

import Model.Deck.Card.ICard;
import Model.Deck.Deck;
import Model.Deck.Exception.DeckException;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeckTests {
    private Deck deck = null;
    private String jsonPath = "./Deck.json";

    @Test
    public void testDeckInit() {  
        try {
            deck = new Deck(jsonPath);
        } catch (DeckException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDeckSize() {
    	try {
    		deck = new Deck(jsonPath);
    		int size = 30;
    		assertEquals("Size of the deck must be " + size, size, (int) deck.size());
    	}
    	catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testAddCard() {
        try {
        	deck = new Deck(jsonPath);
            ICard pickedCard = deck.withdraw();
            Integer initialDeckSize = deck.size();

            deck.add(pickedCard);
            Integer afterDeckSize = deck.size();

            if (!(afterDeckSize == initialDeckSize + 1)) {
                fail("After add(), the deck size have not been incremented.");
            }
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
}
