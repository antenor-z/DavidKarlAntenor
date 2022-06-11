package Model;

import java.util.ArrayList;

public class OnlyGoToJailCardDeck extends Deck {
	OnlyGoToJailCardDeck() throws DeckException {
        super("./Deck.json");
        this.cards = new ArrayList<ACard>();
        this.cards.add(new GoToJailCard("desc", "name", "path"));
    }
}
