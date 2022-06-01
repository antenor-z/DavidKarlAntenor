package Model;

import java.util.ArrayList;

public class Company extends Tile {
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
	void buyCompany(Player player) throws CompanyException, PlayerException
	{
		if(this.owner == null)
		{
			owner = player;
			owner.addOrSubCash(-price);
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
				int ammountToPay = priceRate * diceNumber;
				try {
					tenant.addOrSubCash(-ammountToPay);
					this.owner.addOrSubCash(ammountToPay);
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
			ret.add("Owner: " + owner.getName());
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
}
