package Model.Deck.Card;

public class MoveCard extends ValueCard {
    public MoveCard(String description, String name, Integer value) {
        super(description, name, value);
        _type = CardType.MOVE;
    }
}
