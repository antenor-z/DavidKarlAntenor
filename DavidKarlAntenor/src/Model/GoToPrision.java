package Model;
import java.util.ArrayList;

class GoToPrision extends Tile{
	GoToPrision() {
		super(TileType.GoToPrision);
	}
	ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("GoToPrision");
		return ret;
	}
	void gotoPrision(Player player) {
		if (getCardOwner() == player)
		{
			setCardOwner(null);
		}
		else
		{
			player.goToTile(10);
		}
	}
}
