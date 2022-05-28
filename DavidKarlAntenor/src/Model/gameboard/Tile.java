package Model.gameboard;

import java.util.ArrayList;

import Model.TileType;

public class Tile {
	public TileType tileType;
	public Tile(TileType tileType){
		this.tileType = tileType;
	}
	public ArrayList<String> print() {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Tile");
		return ret;
	}
}
