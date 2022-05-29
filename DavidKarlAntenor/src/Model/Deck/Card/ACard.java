package Model.Deck.Card;

public abstract class ACard implements Model.Deck.Card.ICard {
    private final String _description;
    private final String _name;
    private final String _image;
    protected CardType _type;

    public ACard(String _description, String _name, String _image) {
    	this._image = _image;
        this._description = _description;
        this._name = _name;
    }

    @Override
    public final String getDescription() {
        return _description;
    }

    @Override
    public final String getName() {
        return _name;
    }

    @Override
    public final String toString() {
        return _name;
    }
    
    public final String getImage() {
    	return _image;
    }

    @Override
    public final CardType getType() {
        return _type;
    }
}
