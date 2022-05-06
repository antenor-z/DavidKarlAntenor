package Model.Deck.Tests;

import Model.Deck.Card.ICard;
import Model.Deck.Deck;

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeckTests {
    Deck deck = null;

    @Test
    public void testDeckInit() {
        String jsonPath = "./Deck.json";
        try {
            deck = new Deck(jsonPath);
        } catch (Deck.Exception.DeckException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDeckSize() {
        int size = 30;
        assertEquals("Size of the deck must be " + size, size, (int) deck.size());
    }

    @Test
    public void testWithdraw() {
        try {
            Integer initialDeckSize = deck.size();
            ICard pickedCard = deck.withdraw();
            Integer afterDeckSize = deck.size();

            if (pickedCard == null) {
                fail("Null card has been withdrawn.");
            }
            if (!(initialDeckSize == afterDeckSize + 1)) {
                fail("After withdraw(), the deck size have not been decremented.");
            }
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testAddCard() {
        try {
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
