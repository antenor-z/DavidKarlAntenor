package Model.gameboard;
import java.util.ArrayList;

import Model.TileType;
import Model.Player.Player;
import Model.Player.PlayerException;

public class Land extends Tile {
	private String description;
	private int price;
	private int buildHouseCost;
	private int buildHotelCost;
	private int numberOfHouses;
	private boolean hasHotel;
	private int[] rentCost = new int[6];
	private Player owner;

	public Land(String description, int price, int buildHouseCost, int buildHotelCost, int[] rentCost)
	{
		super(TileType.Land);
		this.description = description;
		this.price = price;
		this.buildHouseCost = buildHouseCost;
		this.buildHotelCost = buildHotelCost;
		this.rentCost = rentCost;	
	}

	public void buyLand(Player player) throws LandException, PlayerException
	{
		if(this.owner == null)
		{
			owner = player;
			owner.addOrSubCash(-price);
		}
		else
		{
			throw new LandException("Land already has a owner: " 
		+ player.getColor());
		}
	}
	
	public void buildHouse() throws PlayerException, LandException
	{
		if(this.owner != null)
		{		
			if(numberOfHouses <= 3)
			{
				owner.addOrSubCash(-buildHouseCost);
				numberOfHouses++;
			}
			else
			{
				throw new LandException("Max 4 houses");
			}
		}
		else
		{
			throw new LandException("This land has no owner.");
		}
	}
	
	public void buildHotel() throws PlayerException, LandException
	{
		if(numberOfHouses >= 1)
		{
			if(hasHotel == false)
			{
				owner.addOrSubCash(-buildHotelCost);
				hasHotel = true;
			}
			else
			{
				throw new LandException("Already has a hotel");
			}
		}
		else
		{
			throw new LandException("At least 1 house to build hotel");
		}
	}
	public void payRent(Player tenant) throws LandException, PlayerException
	{
		if(this.owner != null)
		{
			if(this.owner != tenant)
			{
				int ammountToPay;
				if(hasHotel) 
					ammountToPay = rentCost[5];
				else
					ammountToPay = rentCost[numberOfHouses];
				tenant.addOrSubCash(-ammountToPay);
				this.owner.addOrSubCash(ammountToPay);
			}
			else 
			{
				throw new LandException("Player and tenant are the same person");
			}
		}
		else
		{
			throw new LandException("Land does not have a owner");
		}
	}
	public Player getOwner()
	{
		return owner;
	}
	ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Land");
		ret.add("Description: " + description);
		ret.add("price: " + price);
		ret.add("Build house cost: " + buildHouseCost);
		ret.add("Build hotel cost: " + buildHotelCost);
		ret.add("Rent cost 0 houses: " + rentCost[0]);
		ret.add("Rent cost 1 houses: " + rentCost[1]);
		ret.add("Rent cost 2 houses: " + rentCost[2]);
		ret.add("Rent cost 3 houses: " + rentCost[3]);
		ret.add("Rent cost 4 houses: " + rentCost[4]);
		ret.add("Rent cost hotel: " + rentCost[5]);
		return ret;
	}
}
