package Model.gameboard;
import java.util.ArrayList;

import Model.TileType;
import Model.Player.Player;

public class GoToPrision extends Tile{
	public GoToPrision() {
		super(TileType.GoToPrision);
	}
	ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("GoToPrision");
		return ret;
	}
	void gotoPrision(Player player) {
		player.goToTile(10);
	}
}
