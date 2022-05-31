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

	public Boolean canBuyLand()
	{
		if(this.owner == null)
			return true;
		return false;
	}
	
	public Boolean canBuildHouse(Player player)
	{
		if(this.owner == player)
		{		
			if(numberOfHouses <= 3)
			{
				return true;
			}
		}
		return false;
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
	public ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Land");
		ret.add(description);
		ret.add("Price: " + price);
		ret.add("Build house: " + buildHouseCost);
		ret.add("Build hotel: " + buildHotelCost);
		if(hasHotel)
		{
			ret.add("RentCost: " + rentCost[5]);
		}
		else
		{
			ret.add("RentCost: " + rentCost[numberOfHouses]);
		}
	
		ret.add("");
		if(owner != null)
			ret.add("owner: " + owner.getName());
		else
			ret.add("owner: " + "---");
		ret.add("Number of houses: " + numberOfHouses);
		ret.add("Has Hotel: " + hasHotel);
		return ret;
	}

	public boolean canBuildHotel(Player turn) {
		if(numberOfHouses >= 1)
		{
			if(hasHotel == false)
			{
				return true;
			}
		}
		return false;
	}

	public int getNumberOfHouses() {
		return numberOfHouses;
	}

	public boolean hasHotel() {
		return hasHotel;
	}
}
