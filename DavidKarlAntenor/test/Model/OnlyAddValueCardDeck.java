package Model;

import java.util.ArrayList;

public class OnlyAddValueCardDeck extends Deck {
	OnlyAddValueCardDeck() throws DeckException {
        super("./Deck.json");
        this.cards = new ArrayList<ACard>();
        this.cards.add(new ValueCard("desc", "name", 200, "path"));
    }
}
