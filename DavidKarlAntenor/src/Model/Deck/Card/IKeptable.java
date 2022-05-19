package Model.Deck.Card;

public interface IKeptable {
	public void pick(Model.Player.Player p);
	public Model.Player.Player getOwner();
	public void use();
}
