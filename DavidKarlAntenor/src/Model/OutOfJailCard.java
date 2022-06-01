package Model;

class OutOfJailCard extends ACard{
	private Model.Player owner;
    public OutOfJailCard(String description, String name, String imagePath) {
        super(description, name, imagePath);
        _type = CardType.OUT_OF_JAIL;
        owner = null;
    }
    public void pick(Model.Player p) throws DeckException {
    	if(owner == null) 
    	{
    		owner = p;
    	}
    	else
    	{
    		throw new DeckException("OutofJailCard owner: " + owner.getColor() 
    		+ " player trying to pick" + p.getColor());
    	}
    }
    public Model.Player getOwner() {
    	return owner;
    }
    public void use(Model.Player p) throws DeckException {
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
	@Override
	public int getValue() {
		return 0;
	}
}
