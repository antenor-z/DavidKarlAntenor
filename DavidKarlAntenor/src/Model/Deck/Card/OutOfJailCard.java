package Model.Deck.Card;

public class OutOfJailCard extends ACard implements IKeptable {
    public OutOfJailCard(String description, String name) {
        super(description, name);
        _type = CardType.OUT_OF_JAIL;
    }
}
