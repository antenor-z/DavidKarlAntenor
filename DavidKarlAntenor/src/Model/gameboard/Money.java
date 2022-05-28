package Model.gameboard;

import Model.Player.Player;
import Model.Player.PlayerException;

import java.util.ArrayList;

import Model.TileType;
public class Money extends Tile {
	private int ammount;

	public Money (int ammount)
	{
		super(TileType.Money);
		this.ammount = ammount;
	}
	public void execute(Player player) {
		try
		{
			player.addOrSubCash(ammount);
		}
		catch (PlayerException e)
		{
			System.out.println(e.getMessage());
		}
	}
	ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Money");
		ret.add("Ammount" + ammount);
		return ret;
	}
}
