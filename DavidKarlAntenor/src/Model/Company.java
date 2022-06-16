package Model;

import java.util.ArrayList;

class Company extends Tile {
	private int price;
	private Player owner;
	private int priceRate;
	private String description;
	Company(String description, int price, int priceRate)
	{
		super(TileType.Company);
		this.description = description;
		this.price = price;
		this.priceRate = priceRate;
		this.owner = null;
	}
	Company(String description, int price, int priceRate, Player owner)
	{
		super(TileType.Company);
		this.description = description;
		this.price = price;
		this.priceRate = priceRate;
		this.owner = owner;
	}
	void buyCompany(Player player) throws CompanyException, PlayerException
	{
		if(this.owner == null)
		{
			owner = player;
			System.out.println(owner.getName() + " had " + owner.getCash());
			owner.addOrSubCash(-price);
			System.out.println(description + " bought by " + owner.getName());
			System.out.println(owner.getName() + " now has " + owner.getCash());
		}
		else
			throw new CompanyException("Company already has a owner: " 
		+ player.getColor());
	}
	void payRent(Player tenant, int diceNumber) throws CompanyException
	{
		if(this.owner != null)
		{
			if(this.owner != tenant)
			{
				System.out.println("Tenant (" + tenant.getName() + ") had " + tenant.getCash());
				System.out.println("Owner (" + owner.getName()+ ") had " + owner.getCash());
				int ammountToPay = priceRate * diceNumber;
				try {
					tenant.addOrSubCash(-ammountToPay);
					this.owner.addOrSubCash(ammountToPay);
					System.out.println(tenant.getName() + " payed " + ammountToPay + " to " + owner.getName());
					System.out.println("Tenant now has " + tenant.getCash());
					System.out.println("Owner now has " + owner.getCash());
				}
				catch (PlayerException e)
				{
					System.out.println("Failed to pay rent.");
					System.out.print(e.getMessage());
				}
			}
			else
			{
				throw new CompanyException("Cannot pay rent to themself: " + owner.getColor());
			}
		}
		else
		{
			throw new CompanyException("Company does not have a owner.");
		}
	}
	ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("company");
		if(owner != null)
			ret.add("Owner: " + owner.getColor().toString());
		else
			ret.add("Owner: ---");
		ret.add(description);
		ret.add("Price: " + price);
		ret.add("Price rate: " + priceRate);
		return ret;
	}
	Player getOwner()
	{
		return owner;
	}
	String getName() {
		return description;
	}
	Boolean canBuyCompany()
	{
		if(this.owner == null)
			return true;
		return false;
	}
}
