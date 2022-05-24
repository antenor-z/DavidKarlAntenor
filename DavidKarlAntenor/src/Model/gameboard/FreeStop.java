package Model.gameboard;
import Model.TileType;

public class FreeStop extends Tile {
	public FreeStop() {
		super(TileType.FreeStop);
	}
	void print(){
		System.out.println("I'm a freestop tile");
	}
}
