package Model;

import java.util.ArrayList;

public class Tile {
	public TileType tileType;
	public Tile(TileType tileType){
		this.tileType = tileType;
	}
	ArrayList<String> print() {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Tile");
		return ret;
	}
}
