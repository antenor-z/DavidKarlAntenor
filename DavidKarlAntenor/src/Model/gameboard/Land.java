package Model.gameboard;

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
		this.description = description;
		this.price = price;
		this.buildHouseCost = buildHouseCost;
		this.buildHotelCost = buildHotelCost;
		this.rentCost = rentCost;	
	}
	public void buyLand(Player player) throws LandException
	{
		if(this.owner == null)
		{
			owner = player;
		}
		else
		{
			throw new LandException("Land already has a owner: " + player.getColor());
		}
	}
	
	public void buildHouse() throws PlayerException, LandException
	{
		if(this.owner != null)
		{		
			if(numberOfHouses <= 3)
			{
				owner.addOrSubCash(-buildHotelCost);
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
	public void payRent(Player player) throws LandException, PlayerException
	{
		if(this.owner != null)
		{
			if(this.owner != player)
			{
				int ammountToPay;
				if(hasHotel) {
					ammountToPay = rentCost[5];
				}
				else
				{
					ammountToPay = rentCost[numberOfHouses];
				}
				player.addOrSubCash(-ammountToPay);
				this.owner.addOrSubCash(ammountToPay);
			}
			else
			{
				throw new LandException("Player and owner are the same person");
			}
		}
		else
		{
			throw new LandException("Land does not have a owner");
		}
	}
	Player getOwner()
	{
		return owner;
	}
	void print(){
		System.out.println("I'm a land");
		System.out.println("Description: " + description);
		System.out.println("price: " + price);
		System.out.println("Build house cost: " + buildHouseCost);
		System.out.println("Build hotel cost: " + buildHotelCost);
		System.out.println("Rent cost 0 houses: " + rentCost[0]);
		System.out.println("Rent cost 1 houses: " + rentCost[1]);
		System.out.println("Rent cost 2 houses: " + rentCost[2]);
		System.out.println("Rent cost 3 houses: " + rentCost[3]);
		System.out.println("Rent cost 4 houses: " + rentCost[4]);
		System.out.println("Rent cost 5 houses: " + rentCost[5]);
	}
}
