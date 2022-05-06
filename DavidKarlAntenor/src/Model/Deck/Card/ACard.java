package Model.Deck.Card;

public abstract class ACard implements Model.Deck.Card.ICard {
    private final String _description;
    private final String _name;

    public ACard(String _description, String _name) {
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
}