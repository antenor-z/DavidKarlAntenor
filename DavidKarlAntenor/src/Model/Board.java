package Model;
import org.json.*;

import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
class Board {
	private final ArrayList<Tile> tiles = new ArrayList<Tile>();
	private Deck deck;
	
	Board(ArrayList<Player> playerList) {	
		try {
			deck = new Deck("./Deck.json");
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		
		try {
			String content = Files.readString(Path.of("./Board.json"));
			JSONObject obj = new JSONObject(content);
            JSONArray jsonBoard = obj.getJSONArray("board");
            
            for (Object jsonTileObj: jsonBoard) {
            	JSONObject jsonTile = (JSONObject)jsonTileObj;
            	String type = jsonTile.getString("type");
            
            	if(type.equals("Land"))
            	{  		
            		String description = jsonTile.getString("description");
            		int price = jsonTile.getInt("price");
            		int buildHouseCost = jsonTile.getInt("buildHouseCost");
            		int buildHotelCost = jsonTile.getInt("buildHotelCost");
            		String imgPath = jsonTile.getString("img");
            		JSONArray jsonRentCost = jsonTile.getJSONArray("rentCost");
            		int rentCost[] = new int[6];
            		for(int i = 0; i < 6; i++)
            		{
            			rentCost[i] = jsonRentCost.getInt(i);
            		}
            		tiles.add(new Land(description, price, buildHouseCost, 
            				buildHotelCost, rentCost, imgPath));
            	}
            	else if(type.equals("Company"))
            	{
            		String description = jsonTile.getString("description");
            		int price = jsonTile.getInt("price");
            		int priceRate = jsonTile.getInt("priceRate");
            		tiles.add(new Company(description, price, priceRate));
            	}
            	else if(type.equals("Prision"))
            	{
            		tiles.add(new Prision());
            	}
            	else if(type.equals("LuckSetback"))
            	{
            		tiles.add(new LuckSetback(playerList, deck));
            	}
            	else if(type.equals("Money"))
            	{
            		int ammount = jsonTile.getInt("ammount");
            		tiles.add(new Money(ammount));
            	}
            	else if(type.equals("FreeStop"))
            	{
            		tiles.add(new FreeStop());
            	}
            	else if(type.equals("GoToPrision"))
            	{
            		tiles.add(new GoToPrision());
            	}
            	else if(type.equals("Start"))
            	{
            		tiles.add(new Start());
            	} 
            }
		}
		catch(Exception e) {
			System.out.println("Failed to read Board.json");
			System.out.println(e.getMessage());
		}	
	}
	Board(JSONObject saveGameJSON) {
		ArrayList<Player> playerList = GameState.getInstance().players;
		try {
			deck = new Deck("./Deck.json");
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		
		try {
			String content = Files.readString(Path.of("./Board.json"));
			JSONObject obj = new JSONObject(content);
            JSONArray jsonBoard = obj.getJSONArray("board");
            JSONObject savedCompanies = saveGameJSON.getJSONObject("Companies");
            JSONObject savedLands = saveGameJSON.getJSONObject("Lands");
            
            for (Object jsonTileObj: jsonBoard) {
            	JSONObject jsonTile = (JSONObject)jsonTileObj;
            	String type = jsonTile.getString("type");
            
            	if(type.equals("Land"))
            	{  		
            		String description = jsonTile.getString("description");
            		int price = jsonTile.getInt("price");
            		int buildHouseCost = jsonTile.getInt("buildHouseCost");
            		int buildHotelCost = jsonTile.getInt("buildHotelCost");
            		JSONArray jsonRentCost = jsonTile.getJSONArray("rentCost");
            		String imgPath = jsonTile.getString("img");
            		int rentCost[] = new int[6];
            		for(int i = 0; i < 6; i++)
            		{
            			rentCost[i] = jsonRentCost.getInt(i);
            		}
            		String tileIndex = String.valueOf(tiles.size());
            		JSONObject landObj = (JSONObject) savedLands.get(tileIndex);
            		int numberOfHouses = landObj.getInt("NumberOfHouses");
            		boolean hasHotel = landObj.getBoolean("HasHotel");
            		
            		Player owner = null;
            		if(landObj.isNull("Owner") == false)
            		{
            			String ownerName = landObj.getString("Owner");
                		
                		for(Player p: GameState.getInstance().players)
                		{
                			if (ownerName.equals(p.getName()))
                			{
                				owner = p;
                			}
                		}
            		}
            		
            		tiles.add(new Land(description, price, buildHouseCost, 
            				buildHotelCost, rentCost, imgPath, owner, numberOfHouses, hasHotel));
            	}
            	else if(type.equals("Company"))
            	{
            		String description = jsonTile.getString("description");
            		int price = jsonTile.getInt("price");
            		int priceRate = jsonTile.getInt("priceRate");
            		
            		String tileIndex = String.valueOf(tiles.size());
            		JSONObject CompanyObj = (JSONObject) savedCompanies.get(tileIndex);
            		Player owner = null;
            		if(CompanyObj.isNull("Owner") == false)
            		{
            			String ownerName = CompanyObj.getString("Owner");
                		
                		for(Player p: GameState.getInstance().players)
                		{
                			if (ownerName.equals(p.getName()))
                			{
                				owner = p;
                			}
                		}
            		}
            		
            		tiles.add(new Company(description, price, priceRate, owner));
            	}
            	else if(type.equals("Prision"))
            	{
            		tiles.add(new Prision());
            	}
            	else if(type.equals("LuckSetback"))
            	{
            		tiles.add(new LuckSetback(playerList, deck));
            	}
            	else if(type.equals("Money"))
            	{
            		int ammount = jsonTile.getInt("ammount");
            		tiles.add(new Money(ammount));
            	}
            	else if(type.equals("FreeStop"))
            	{
            		tiles.add(new FreeStop());
            	}
            	else if(type.equals("GoToPrision"))
            	{
            		tiles.add(new GoToPrision());
            	}
            	else if(type.equals("Start"))
            	{
            		tiles.add(new Start());
            	} 
            }
		}
		catch(Exception e) {
			System.out.println("Failed to read Board.json");
			e.printStackTrace();
		}	
	}
	int getLength() {
		return tiles.size();
	}
	Tile getTile(int i) {
		return tiles.get(i);
	}
	void printAllTiles() {
		for(Tile tile: tiles) {
			tile.print();
		}
	}
}
