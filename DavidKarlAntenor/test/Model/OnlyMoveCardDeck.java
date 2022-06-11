package Model;

import java.util.ArrayList;

public class OnlyMoveCardDeck extends Deck {
	OnlyMoveCardDeck() throws DeckException {
        super("./Deck.json");
        this.cards = new ArrayList<ACard>();
        this.cards.add(new MoveCard("desc", "name", 1, "path"));
    }
}
