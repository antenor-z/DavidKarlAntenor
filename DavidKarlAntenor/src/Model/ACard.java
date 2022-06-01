package Model;

public abstract class ACard{
    private final String _description;
    private final String _name;
    private final String _image;
    protected CardType _type;

    ACard(String _description, String _name, String _image) {
    	this._image = _image;
        this._description = _description;
        this._name = _name;
    }

    final String getDescription() {
        return _description;
    }

    final String getName() {
        return _name;
    }

    final String getImage() {
    	return _image;
    }

    final CardType getType() {
        return _type;
    }

	int getValue() {
		return 0;
	}
}
