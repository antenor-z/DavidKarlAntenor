// Part of API
package Model;

public class Player {
	private int cash;
	private int currentTileN;
	private final int boardSize = GameSettings.getInstance().getBoardSize();
	private PlayerColor color;
	private String name;
	private boolean isBankrupt = false;
	public Player(int cash, PlayerColor color, String name)
	{
		this.cash = cash;
		this.currentTileN = 0;
		this.color = color;
		this.name = name;
	}
	public Player(int cash, PlayerColor color, String name, boolean isBankrupt)
	{
		this.cash = cash;
		this.currentTileN = 0;
		this.color = color;
		this.name = name;
		this.isBankrupt = isBankrupt;
	}
	public void goFoward(int n) throws PlayerException 
	{
		if(n <= 0) 
		{
			throw new PlayerException("Can't go backwards");
		}
		else
		{
			currentTileN = (currentTileN + n) % boardSize;
		}
	}
	
	public int getCash()
	{
		return cash;
	}

	public void addOrSubCash(int ammount) throws PlayerException
	{
		if(cash + ammount >= 0)
		{
			cash += ammount;
		}
		else
		{
			throw new PlayerException("Cash less than zero");
		}
	}
	
	public int getTileNumber()
	{
		return currentTileN;
	}
	
	public PlayerColor getColor()
	{
		return color;
	}
	
	public void goToTile(int tile)
	{
		currentTileN = tile;
	}
	
	public String getName() {
		return name;
	}
	public String toString() {
		String s = "Player: " + getName() + "\n";
		s += "  Color: " + getColor() + "\n";
		s += "  @Tile: " + currentTileN + "\n";
		s += "  Balance: " + cash + "\n";
		return s;
	}
	public boolean isBankrupt() {
		return isBankrupt;
	}
}
