package Model;

class ValueCard extends ACard {
    private final Integer _value;

    ValueCard(String description, String name, Integer value, String imagePath) {
        super(description, name, imagePath);
        _value = value;
        _type = CardType.VALUE;
    }

    final int getValue() {
        return _value;
    }
}
