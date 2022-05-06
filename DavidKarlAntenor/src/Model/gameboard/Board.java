package Model.gameboard;
import org.json.*;
import Model.Utils.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {
	private Tile[] tiles;
	private final int numberOfTiles = 40;
	public Board()
	{
		String jsonString = Files.getFileContent("Board.json");
		JSONObject obj = new JSONObject(jsonString);
		JSONArray jsonTiles = obj.getJSONArray("tiles");
	}
	public Tile[] getTiles(){
		return tiles;
	}
}
