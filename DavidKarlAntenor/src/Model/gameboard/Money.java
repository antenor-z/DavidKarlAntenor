package Model.gameboard;

import Model.Player.Player;
import Model.Player.PlayerException;

public class Money extends Tile {
	private int ammount;
	public Money (int ammount)
	{
		this.ammount = ammount;
	}
	public void execute(Player player) {
		try
		{
			player.changeCash(ammount);
		}
		catch (PlayerException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
