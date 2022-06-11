package Model;

import java.util.ArrayList;

public class OnlyOutOfJailCardDeck extends Deck {
	OnlyOutOfJailCardDeck() throws DeckException {
        super("./Deck.json");
        this.cards = new ArrayList<ACard>();
        this.cards.add(new OutOfJailCard("desc", "OutOfJail", "path"));
    }
}
