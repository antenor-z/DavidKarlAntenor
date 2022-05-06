package Model.Deck.Card;

public class ValuePerPlayer extends ValueCard {
    public ValuePerPlayer(String description, String name, Integer value) {
        super(description, name, value);
        _type = CardType.VALUE_PER_PLAYER;
    }
}
