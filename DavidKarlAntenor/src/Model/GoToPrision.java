package Model;
import java.util.ArrayList;

public class GoToPrision extends Tile{
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
		player.goToTile(10);
	}
}
