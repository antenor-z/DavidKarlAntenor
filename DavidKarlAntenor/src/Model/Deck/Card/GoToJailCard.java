package Model.Deck.Card;

public class GoToJailCard extends ACard {
    public GoToJailCard(String description, String name) {
        super(description, name);
        _type = CardType.GO_TO_JAIL;
    }
}
