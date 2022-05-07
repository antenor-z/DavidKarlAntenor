package Model.gameboard;

import Model.Player.Player;
import Model.Player.PlayerException;

public class Company extends Tile {
	private int price;
	private Player owner;
	private int priceRate;
	String description;
	public Company(String description, int price, int priceRate)
	{
		this.description = description;
		this.price = price;
		this.priceRate = priceRate;
		this.owner = null;
		System.out.println("I'm a company");
		System.out.println("Description: " + description);
		System.out.println("price: " + price);
		System.out.println("price rate: " + priceRate);
		
	}
	public void buyCompany(Player player) throws CompanyException
	{
		if(this.owner == null)
		{
			owner = player;
		}
		else
		{
			throw new CompanyException("Company already has a owner");
		}
	}
	public void payRent(Player player, int diceNumber) throws CompanyException
	{
		if(this.owner != null)
		{
			int ammountToPay = priceRate * diceNumber;
			try {
				player.changeCash(-ammountToPay);
				this.owner.changeCash(ammountToPay);
			}
			catch (PlayerException e)
			{
				System.out.print(e.getMessage());
			}
		}
		else
		{
			throw new CompanyException("Company does not have a owner");
		}
	}
}
