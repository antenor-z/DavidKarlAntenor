package Model.gameboard;

import Model.Player.Player;
import Model.Player.PlayerException;

public class Company extends Tile {
	int price;
	Player owner;
	int priceRate;
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
				player.withdraw(ammountToPay);
				this.owner.deposit(ammountToPay);
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
