package Model;

import java.util.ArrayList;

public class OnlyValuePerPlayerDeck extends Deck {
    OnlyValuePerPlayerDeck() throws DeckException {
        super("./Deck.json");
        this.cards = new ArrayList<ACard>();
        this.cards.add(new ValuePerPlayer("desc", "name", 1, "path"));
    }
}
