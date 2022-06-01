package Model;

class GoToJailCard extends ACard {
    public GoToJailCard(String description, String name, String imagePath) {
        super(description, name, imagePath);
        _type = CardType.GO_TO_JAIL;
    }

	int getValue() {
		return 0;
	}
}
