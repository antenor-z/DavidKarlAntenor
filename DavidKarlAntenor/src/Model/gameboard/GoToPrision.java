package Model.gameboard;
import Model.TileType;
import Model.Player.Player;

public class GoToPrision extends Tile{
	public GoToPrision() {
		super(TileType.GoToPrision);
	}
	void print(){
		System.out.println("I'm a GoToPrision tile");
	}
	void gotoPrision(Player player) {
		player.goToTile(10);
	}
}
