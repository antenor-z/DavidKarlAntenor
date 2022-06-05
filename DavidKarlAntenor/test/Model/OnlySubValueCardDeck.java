package Model;

import java.util.ArrayList;

public class OnlySubValueCardDeck extends Deck {
	OnlySubValueCardDeck() throws DeckException {
        super("./Deck.json");
        this.cards = new ArrayList<ACard>();
        this.cards.add(new ValueCard("desc", "name", -200, "path"));
    }
}
