package Model.Deck.Card;

public interface ICard {
    String getDescription();
    String getName();
    CardType getType();
	int getValue();
}
