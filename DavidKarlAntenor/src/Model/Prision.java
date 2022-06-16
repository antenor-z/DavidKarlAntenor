package Model;

import java.util.ArrayList;
class Prision extends Tile {
	public Prision() {
		super(TileType.Prision);
	}
	public ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Prision");
		return ret;
	}
	public void getOut(Player player, int diceNumber1, int diceNumber2) throws PlayerException {
		
		if(diceNumber1 == diceNumber2) {
			player.goFoward(diceNumber1 + diceNumber2);
		}
		
	}
	public void processCard(Player p)
	{
		if(Tile.getCardOwner() == p)
		{
			setCardOwner(null);
			try {
				p.goFoward(1);
			} catch (PlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
