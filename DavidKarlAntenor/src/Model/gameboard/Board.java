package Model.gameboard;
import org.json.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Board {
	private Tile[] tiles;
	public Board()
	{	
		tiles = new Tile[40];
		try
		{
			String content = Files.readString(Path.of("./Board.json"));
			JSONObject obj = new JSONObject(content);
            JSONArray jsonBoard = obj.getJSONArray("board");
            
            for (Object jsonTile: jsonBoard) {
                
            	JSONObject jsonTileObj = (JSONObject)jsonTile;
            	String type = jsonTileObj.getString("type");
            	int position = jsonTileObj.getInt("position");
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
            		//tiles[position] = new Land(description, price, buildHouseCost, buildHotelCost, rentCost);
            	}
            	else if(type.equals("Company"))
            	{
            		String description = jsonTileObj.getString("description");
            		int price = jsonTileObj.getInt("price");
            		int priceRate = jsonTileObj.getInt("priceRate");
            		tiles[position] = new Company(description, price, priceRate);
            	}
            	else if(type.equals("prision"))
            	{
            		tiles[position] = new Prision();
            	}
            	else if(type.equals("LuckSetBack"))
            	{
            		tiles[position] = new Lucksetback();
            	}
            	else if(type.equals("Money"))
            	{
            		int ammount = jsonTileObj.getInt("ammount");
            		tiles[position] = new Money(ammount);
            	}
            	else if(type.equals("Freestop"))
            	{
            		tiles[position] = new Freestop();
            	}
            	else if(type.equals("GoToPrision"))
            	{
            		tiles[position] = new GoToPrision();
            	}
            	else if(type.equals("Start"))
            	{
            		tiles[position] = new Start();
            	}
          
            }
           
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}
	public Tile[] getTiles(){
		return tiles;
	}
}
