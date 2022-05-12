package Model.gameboard;

import Model.Player.Player;
import Model.Player.PlayerException;

public class Start extends Tile {
	void print(){
		System.out.println("I'm a Start tile");
	}
	void execute(Player player) throws PlayerException
	{
		player.addOrSubCash(200);
	}
}
