package Model.Deck.Card;

public class ValueCard extends ACard {
    private final Integer _value;

    public ValueCard(String description, String name, Integer value) {
        super(description, name);
        _value = value;
    }

    public final Integer getValue() {
        return _value;
    }
}
