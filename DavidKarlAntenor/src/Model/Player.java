package Model;

public class Player {
	private int cash;
	private int currentTile;
	private final int boardSize;
	private PlayerColor color;
	private String name;
	public Player(int cash, int boardSize, PlayerColor color, String name)
	{
		this.cash = cash;
		this.currentTile = 0;
		this.boardSize = boardSize;
		this.color = color;
		this.name = name;
	}
	public void goFoward(int n) throws PlayerException 
	{
		if(n <= 0) 
		{
			throw new PlayerException("Can't go backwards");
		}
		else
		{
			currentTile = (currentTile + n) % boardSize;
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
	
	public int getCurrentTile()
	{
		return currentTile;
	}
	
	public PlayerColor getColor()
	{
		return color;
	}
	
	public void goToTile(int tile)
	{
		currentTile = tile;
	}
	
	public String getName() {
		return name;
	}
	public String dump() {
		String s = "Player: " + getName() + "\n";
		s += "  Color: " + getColor() + "\n";
		s += "  @Tile: " + currentTile + "\n";
		s += "  Balance: " + cash + "\n";
		return s;
	}
}
