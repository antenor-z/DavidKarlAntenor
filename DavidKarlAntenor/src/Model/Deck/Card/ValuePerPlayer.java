package Model.Deck.Card;

public class ValuePerPlayer extends ValueCard {
    public ValuePerPlayer(String description, String name, Integer value, String imagePath) {
        super(description, name, value, imagePath);
        _type = CardType.VALUE_PER_PLAYER;
    }
}
