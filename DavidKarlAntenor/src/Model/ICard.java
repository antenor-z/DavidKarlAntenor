package Model;

public interface ICard {
    String getDescription();
    String getName();
    String getImage();
    CardType getType();
	int getValue();
}
