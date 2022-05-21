package Model.Deck.Card;

import Model.Deck.Exception.DeckException;

public interface IKeptable {
	public void pick(Model.Player.Player p) throws DeckException;
	public Model.Player.Player getOwner();
	public void use(Model.Player.Player p) throws DeckException;
}
