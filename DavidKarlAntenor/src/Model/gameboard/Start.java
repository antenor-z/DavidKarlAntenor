package Model.gameboard;

import Model.Player.Player;
import Model.Player.PlayerException;
import Model.TileType;
public class Start extends Tile {
	public Start() {
		super(TileType.Start);
	}
	void print(){
		System.out.println("I'm a Start tile");
	}
	void execute(Player player) throws PlayerException
	{
		player.addOrSubCash(200);
	}
}
