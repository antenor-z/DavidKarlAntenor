package Model.gameboard;
import org.json.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;

public class Board {
	private final ArrayList<Tile> tiles = new ArrayList<Tile>();
	public Board()
	{	
		try
		{
			String content = Files.readString(Path.of("./Board.json"));
			JSONObject obj = new JSONObject(content);
            JSONArray jsonBoard = obj.getJSONArray("board");
            
            for (Object jsonTile: jsonBoard) {
            	JSONObject jsonTileObj = (JSONObject)jsonTile;
            	String type = jsonTileObj.getString("type");
            	if(type.equals("Land"))
            	{  		
            		String description = jsonTileObj.getString("description");
            		int price = jsonTileObj.getInt("price");
            		int buildHouseCost = jsonTileObj.getInt("buildHouseCost");
            		int buildHotelCost = jsonTileObj.getInt("buildHotelCost");
            		JSONArray jsonRentCost = jsonTileObj.getJSONArray("rentCost");
            		int rentCost[] = new int[6];
            		for(int i = 0; i < 6; i++)
            		{
            			rentCost[i] = jsonRentCost.getInt(i);
            		}
            		tiles.add(new Land(description, price, buildHouseCost, buildHotelCost, rentCost));
            	}
            	else if(type.equals("Company"))
            	{
            		String description = jsonTileObj.getString("description");
            		int price = jsonTileObj.getInt("price");
            		int priceRate = jsonTileObj.getInt("priceRate");
            		tiles.add(new Company(description, price, priceRate));
            	}
            	else if(type.equals("Prision"))
            	{
            		tiles.add(new Prision());
            	}
            	else if(type.equals("LuckSetback"))
            	{
            		tiles.add(new LuckSetback());
            	}
            	else if(type.equals("Money"))
            	{
            		int ammount = jsonTileObj.getInt("ammount");
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
		catch(Exception e)
		{
			System.out.println("Failed to read Board.json");
			System.out.println(e.getMessage());
		}	
	}
	public int getLength() {
		return tiles.size();
	}
	Tile getTile(int i){
		return tiles.get(i);
	}
	void printAllTiles() {
		for(int i = 0; i < 40; i++)
		{
			tiles.get(i).print();
		}
	}
}
