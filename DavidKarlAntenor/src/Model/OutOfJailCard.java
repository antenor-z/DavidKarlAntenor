package Model;

class OutOfJailCard extends ACard{
    public OutOfJailCard(String description, String name, String imagePath) {
        super(description, name, imagePath);
        _type = CardType.OUT_OF_JAIL;
    }
	@Override
	public int getValue() {
		return 0;
	}
}
