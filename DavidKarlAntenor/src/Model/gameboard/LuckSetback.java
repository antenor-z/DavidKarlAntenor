package Model.gameboard;
import Model.Deck.Card.*;

import java.util.ArrayList;

import Model.Deck.Deck;
import Model.Deck.Exception.DeckException;
import Model.Player.Player;
import Model.Player.PlayerException;
public class LuckSetback extends Tile {
	private Deck deck;
	OutOfJailCard outOfJailCard = null;
	ArrayList<Player> playersList;
	public LuckSetback(ArrayList<Player> playersList) throws DeckException
	{
		deck = new Deck("./Deck.json");
		this.playersList = playersList;
	}
	void print(){
		System.out.println("I'm a Lucksetback tile");
	}
	void pickCard(Player p) throws PlayerException {
		ICard card = deck.withdraw();
		
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
			// TODO
		}
		if(card instanceof OutOfJailCard) {
			// TODO
		}
	}
}
