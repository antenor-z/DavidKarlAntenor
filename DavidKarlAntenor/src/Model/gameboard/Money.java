package Model.gameboard;

import Model.Player.Player;
import Model.Player.PlayerException;
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
	void print(){
		System.out.println("I'm a Money tile");
		System.out.println("Ammount" + ammount);
	}
}
