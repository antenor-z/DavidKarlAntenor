package Model;

public class ValueCard extends ACard {
    private final Integer _value;

    public ValueCard(String description, String name, Integer value, String imagePath) {
        super(description, name, imagePath);
        _value = value;
        _type = CardType.VALUE;
    }

    public final int getValue() {
        return _value;
    }
}
