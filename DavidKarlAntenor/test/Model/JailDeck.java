package Model;

import java.util.ArrayList;

public class JailDeck extends Deck {
	int counter = 0;
	
	JailDeck() throws DeckException {
        super("./Deck.json");
        this.cards = new ArrayList<ACard>();
        this.cards.add(new OutOfJailCard("desc", "OutOfJail", "path"));
        this.cards.add(new GoToJailCard("desc", "GoToJail", "path"));
    }
	
	protected ACard withdraw() {
        ACard card = this.cards.get(counter);
        
        counter = (counter + 1) % this.cards.size(); 
        return card;
    }
	
	OutOfJailCard getGoToJailCard() {
		return (OutOfJailCard) this.cards.stream().filter(card -> card.getName().contains("OutOfJail")).findFirst().orElse(null);
	}
}
