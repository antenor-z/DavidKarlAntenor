package Model;

import java.util.ArrayList;
class Money extends Tile {
	private int ammount;

	public Money (int ammount)
	{
		super(TileType.Money);
		this.ammount = ammount;
	}
	public void execute(Player player) {
		try
		{
			System.out.println("Player " + player.getName() + " had " + player.getCash());
			player.addOrSubCash(ammount);
			System.out.println("Player " + player.getName() + " now has " + player.getCash());
		}
		catch (PlayerException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Money");
		ret.add("Ammount" + ammount);
		return ret;
	}

	public int getAmmount() {
		return ammount;
	}
}
