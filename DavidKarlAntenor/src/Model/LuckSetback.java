package Model;
import java.util.ArrayList;
class LuckSetback extends Tile {
	private Deck deck;
	private ArrayList<Player> playersList;
	private ACard currentCard = null;
	private String imagePath;

	LuckSetback(ArrayList<Player> playersList, Deck deck) throws DeckException
	{
		super(TileType.LuckSetback);
		this.deck = deck;
		this.playersList = playersList;
	}
	ArrayList<String> print()
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
	
	void pickCard(Player p) throws PlayerException, DeckException {
		ACard card = deck.withdraw();
		currentCard = card;
		imagePath = card.getImage();
		
		if(card instanceof ValueCard) {
			System.out.println(p.getName() + " had " + p.getCash());
			ValueCard c = (ValueCard)card;
			p.addOrSubCash(c.getValue());
			System.out.println(p.getName() + " now has " + p.getCash() + " because of card");
		}
		if(card instanceof MoveCard) {
			System.out.println(p.getName() + " had " + p.getCash());
			p.addOrSubCash(200);
			p.goToTile(0);
			System.out.println(p.getName() + " now has " + p.getCash() + " because of card");
			System.out.println("And was moved to begining");
		}
		if(card instanceof ValuePerPlayer) {
			for(Player player: playersList)
			{
				if(player != p)
				{
					player.addOrSubCash(-50);
				}
			}
			System.out.println("Each player had to give 50 to " + p.getName());
			p.addOrSubCash(50 * (playersList.size() - 1));
		}
		if(card instanceof GoToJailCard) {
			//GoToJailCard c = (GoToJailCard)card;
			if(getCardOwner() == p)
			{
				setCardOwner(null);
				System.out.println(p.getName() + "got out jail because card");
			}
			else
			{
				System.out.println(p.getName() + "is on jail");
				p.goToTile(10);
			}
		}
		if(card instanceof OutOfJailCard) {
			//System.out.println("Card owner: " + getCardOwner().getName());
			if(getCardOwner() == null) {
				setCardOwner(p);
				System.out.println("Card owner: " + getCardOwner().getName());
			}
			else
			{
				pickCard(p);
			}
		}
	}
}
