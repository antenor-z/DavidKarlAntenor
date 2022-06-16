package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class SaveOpen {
	static public void openGame(Board board, Player turn, ArrayList<Player> players, String path) {
    	try {
			String content = Files.readString(Path.of(path));
			
			JSONObject obj = new JSONObject(content);
            JSONArray jsonPlayers = obj.getJSONArray("Players");
            String currentPlayerName;
            if(!obj.isNull("CurrentPlayer"))
            	currentPlayerName = obj.getString("CurrentPlayer");
            else
            	currentPlayerName = null;
            
            for (Object playerO: jsonPlayers) {
            	JSONObject player = (JSONObject)playerO;
            	String name = player.getString("Name");
            	String colorS = player.getString("Color");
            	PlayerColor color = PlayerColor.valueOf(colorS);
            	int cash = player.getInt("Cash");
            	int tile = player.getInt("@Tile");
            	boolean isBankrupt = player.getBoolean("isBankrupt");
            	Player newPlayer = new Player(cash, color, name, isBankrupt);
            	newPlayer.goToTile(tile);
            	players.add(newPlayer);
            	if(currentPlayerName != null)
            	{
	            	if(currentPlayerName.equals(name))
	            	{
	            		turn = newPlayer;
	            	}
            	}
            }
            board = new Board(obj);
    	} catch(Exception e) {
			System.out.println("Failed to read Board.json");
			e.printStackTrace();
		}	
    }
	static public void saveGame(Board board, Player turn, ArrayList<Player> players, String path)
	{
		JSONObject playerJSON;
		JSONArray playersArrayJSON = new JSONArray();
		
		for(Player player: players)
		{
			playerJSON = new JSONObject();
			playerJSON.put("Name", player.getName());
			playerJSON.put("Color", player.getColor());
			playerJSON.put("Cash", player.getCash());
			playerJSON.put("@Tile", player.getTileNumber());
			playerJSON.put("isBankrupt", player.isBankrupt());
			playersArrayJSON.put(playerJSON);
		}
		
		JSONObject tileJSON;
		JSONObject landsObjectJSON = new JSONObject();
		JSONObject companiesObjectJSON = new JSONObject();
		for(int i = 0; i < board.getLength(); i++)
		{
			if (board.getTile(i) instanceof Land)
			{
				tileJSON = new JSONObject();
				Land land = (Land)board.getTile(i);
				if(land.getOwner() != null)
					tileJSON.put("Owner", land.getOwner().getName());
				else
					tileJSON.put("Owner", JSONObject.NULL);
				tileJSON.put("Name", land.getName());
				tileJSON.put("NumberOfHouses", land.getNumberOfHouses());
				tileJSON.put("HasHotel", land.hasHotel());
				landsObjectJSON.put(String.valueOf(i), tileJSON);
			}
			else if (board.getTile(i) instanceof Company)
			{
				tileJSON = new JSONObject();
				Company company = (Company)board.getTile(i);
				tileJSON.put("Name", company.getName());
				if(company.getOwner() != null)
					tileJSON.put("Owner", company.getOwner().getName());
				else
					tileJSON.put("Owner", JSONObject.NULL);
				companiesObjectJSON.put(String.valueOf(i), tileJSON);
			}
		}
		JSONObject JSONFile = new JSONObject();
		JSONFile.put("Players", playersArrayJSON);
		if(turn != null)
			JSONFile.put("CurrentPlayer", turn.getName());
		else
			JSONFile.put("CurrentPlayer", JSONObject.NULL);
		JSONFile.put("Lands", landsObjectJSON);
		JSONFile.put("Companies", companiesObjectJSON);
		try {
			FileWriter f = new FileWriter(path);
			f.write(JSONFile.toString(2));
			f.close();
		} catch (IOException e) {
			System.out.println("Could not save file.");
			e.printStackTrace();
		}
	}
}
