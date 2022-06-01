package Model;
import java.util.ArrayList;

public class FreeStop extends Tile {
	FreeStop() {
		super(TileType.FreeStop);
	}
	ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("I'm a freestop tile");
		return ret;
	}
}
