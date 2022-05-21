package Model.Deck.Card;

import Model.Deck.Exception.DeckException;

public class OutOfJailCard extends ACard implements IKeptable {
	private Model.Player.Player owner;
    public OutOfJailCard(String description, String name) {
        super(description, name);
        _type = CardType.OUT_OF_JAIL;
        owner = null;
    }
    public void pick(Model.Player.Player p) {
    	owner = p;
    }
    public Model.Player.Player getOwner() {
    	return owner;
    }
    public void use(Model.Player.Player p) throws DeckException {
    	if(p == owner) 
    	{
    		owner = null;
    	}
    	else
    	{
    		throw new DeckException("OutofJailCard owner: " + owner.getColor() 
    		+ " player trying to use" + p.getColor());
    	}
    }
}
