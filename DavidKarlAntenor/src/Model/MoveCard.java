package Model;

public class MoveCard extends ValueCard {
    public MoveCard(String description, String name, Integer value, String imagePath) {
        super(description, name, value, imagePath);
        _type = CardType.MOVE;
    }
}
