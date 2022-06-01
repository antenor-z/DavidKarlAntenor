package Model;

public interface IKeptable {
	public void pick(Model.Player p) throws DeckException;
	public Model.Player getOwner();
	public void use(Model.Player p) throws DeckException;
}
