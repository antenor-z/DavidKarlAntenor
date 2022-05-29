package Model.gameboard;
import Model.Deck.Card.*;
import Model.TileType;

import java.util.ArrayList;

import Model.Deck.Deck;
import Model.Deck.Exception.DeckException;
import Model.Player.Player;
import Model.Player.PlayerException;
public class LuckSetback extends Tile {
	private Deck deck;
	OutOfJailCard outOfJailCard = null;
	ArrayList<Player> playersList;
	ICard currentCard = null;
	String imagePath;

	public LuckSetback(ArrayList<Player> playersList, Deck deck) throws DeckException
	{
		super(TileType.LuckSetback);
		this.deck = deck;
		this.playersList = playersList;
		this.outOfJailCard = deck.getOutOfJailCard();
	}
	public ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("LuckSetback");
		if(currentCard != null)
		{
			ret.add(imagePath);
			if(currentCard.getValue() < 0)
				ret.add("Lose: " + -currentCard.getValue());
			else
				ret.add("Win: " + currentCard.getValue());
		}
		return ret;
	}
	
	public void pickCard(Player p) throws PlayerException, DeckException {
		ICard card = deck.withdraw();
		currentCard = card;
		imagePath = card.getImage();
		
		if(card instanceof ValueCard) {
			ValueCard c = (ValueCard)card;
			p.addOrSubCash(c.getValue());
		}
		if(card instanceof MoveCard) {
			p.addOrSubCash(200);
			p.goToTile(0);
		}
		if(card instanceof ValuePerPlayer) {
			for(Player player: playersList)
			{
				if(player != p)
				{
					player.addOrSubCash(-50);
				}
			}
			p.addOrSubCash(50 * (playersList.size() - 1));
		}
		if(card instanceof GoToJailCard) {
			GoToJailCard c = (GoToJailCard)card;
			if(outOfJailCard.getOwner() == p)
			{
				outOfJailCard.use(p);
			}
			else
			{
				p.goToTile(10);
			}
		}
		if(card instanceof OutOfJailCard) {
			if(outOfJailCard.getOwner() != null) {
				outOfJailCard.pick(p);
			}
			else
			{
				pickCard(p);
			}
		}
	}
}
