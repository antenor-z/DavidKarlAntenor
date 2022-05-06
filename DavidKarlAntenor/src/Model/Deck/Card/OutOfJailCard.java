package Deck.Card;

public class OutOfJailCard extends ACard {
    public OutOfJailCard(String description, String name) {
        super(description, name);
    }

    @Override
    public String getDescription() {
        return null;
    }
}
