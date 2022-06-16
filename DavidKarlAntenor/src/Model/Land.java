package Model;
import java.util.ArrayList;

class Land extends Tile {
	private String description;
	private int price;
	private int buildHouseCost;
	private int buildHotelCost;
	private int numberOfHouses;
	private boolean hasHotel;
	private int[] rentCost = new int[6];
	private Player owner;
	private String imgPath;

	Land(String description, int price, int buildHouseCost, int buildHotelCost, int[] rentCost,
			String imgPath)
	{
		super(TileType.Land);
		this.description = description;
		this.price = price;
		this.buildHouseCost = buildHouseCost;
		this.buildHotelCost = buildHotelCost;
		this.rentCost = rentCost;
		this.owner = null;
		this.imgPath = imgPath;
	}
	
	Land(String description, int price, int buildHouseCost, int buildHotelCost, int[] rentCost,
			String imgPath, Player owner, int numberOfHouses, boolean hasHotel)
	{
		super(TileType.Land);
		this.description = description;
		this.price = price;
		this.buildHouseCost = buildHouseCost;
		this.buildHotelCost = buildHotelCost;
		this.rentCost = rentCost;
		this.owner = owner;
		this.imgPath = imgPath;
		this.numberOfHouses = numberOfHouses;
		this.hasHotel = hasHotel;
	}

	Boolean canBuyLand()
	{
		if(this.owner == null)
			return true;
		return false;
	}
	
	Boolean canBuildHouse(Player player)
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
	
	void buyLand(Player player) throws LandException, PlayerException
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
	
	void buildHouse() throws PlayerException, LandException
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
	
	void buildHotel() throws PlayerException, LandException
	{
		if(this.owner != null)
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
	}
	void payRent(Player tenant) throws LandException, PlayerException
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
	Player getOwner()
	{
		return owner;
	}
	ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Land");
		ret.add(imgPath);
		ret.add("Price: " + price);
		ret.add(description);	
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
			ret.add("owner: " + owner.getColor().toString());
		else
			ret.add("owner: " + "---");
		ret.add("Number of houses: " + numberOfHouses);
		ret.add("Has Hotel: " + hasHotel);
		return ret;
	}

	boolean canBuildHotel(Player turn) {
		if(this.owner == turn)
		{	
			if(numberOfHouses >= 1)
			{
				if(hasHotel == false)
				{
					return true;
				}
			}
		}
		return false;
	}

	int getNumberOfHouses() {
		return numberOfHouses;
	}

	boolean hasHotel() {
		return hasHotel;
	}
	
	String getOwnerColor()
	{
		return owner.getColor().toString();
	}

	public String getName() {
		return description;
	}
}
