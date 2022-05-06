package Deck.Card;

public class ValueCard extends ACard {
    private final Integer _value;

    public ValueCard(String description, String name, Integer value) {
        super(description, name);
        _value = value;
    }

    @Override
    public String getDescription() {
        return null;
    }

    public Integer getValue() {
        return _value;
    }
}
