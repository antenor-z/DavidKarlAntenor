package Model.gameboard;

import java.util.ArrayList;

import Model.TileType;
import Model.Player.Player;
import Model.Player.PlayerException;

public class Company extends Tile {
	private int price;
	private Player owner;
	private int priceRate;
	private String description;
	public Company(String description, int price, int priceRate)
	{
		super(TileType.Company);
		this.description = description;
		this.price = price;
		this.priceRate = priceRate;
		this.owner = null;
	}
	public void buyCompany(Player player) throws CompanyException
	{
		if(this.owner == null)
			owner = player;
		else
			throw new CompanyException("Company already has a owner: " 
		+ player.getColor());
	}
	public void payRent(Player tenant, int diceNumber) throws CompanyException
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
	public ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("company");
		ret.add("description: " + description);
		ret.add("price: " + price);
		ret.add("price rate: " + priceRate);
		return ret;
	}
	public Player getOwner()
	{
		return owner;
	}
}
