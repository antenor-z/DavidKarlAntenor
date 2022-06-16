// Part of API
package Model;
import java.util.ArrayList;

public class GameState implements Model.Observed {
    static GameState instance;
    GameSettings settings = GameSettings.getInstance();
    public ArrayList<Player> players = Players.getInstance().getArray();
    ArrayList<Observer> observers = new ArrayList<Observer>();
    public Board board = new Board(players);
    public Player turn = null;
    public Float duration = 0.f;
    public int[] dices = new int[2];
    int[] dicesPreset = new int[2];
    
    private GameState() {
    	dicesPreset[0] = dicesPreset[1] = -1;
    }
    
    public void setDicePreset(int n, int value) 
    {
    	this.dicesPreset[n] = value;
		update();
    }

    public ArrayList<ArrayList<Object>> getFormatedLandsCompany()
    {
    	return board.getFormatedLandsCompany();
    }
    
    public void dump() {
    	String s = "";
    	for(Player player: players) {
    		if(turn == player) {
    			s += "CURRENT PLAYER BELLOW:\n";
    		}
    		s += player.toString();
    	}
    	System.out.println(s);
    }
    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }
    
    public void addPlayer(String name, PlayerColor color) throws GameException {
    	Players.getInstance().addPlayer(name, color);
    }
    
    public boolean isCardOwner(Player p)
    {
		if(Tile.getCardOwner() == p)
    		return true;
    	return false;
    }
    public ArrayList<String> turnGetPropeties()
    {
    	ArrayList<String> ret = new ArrayList<String>();
    	for(int i = 0; i < board.getLength(); i++)
    	{
    		if(board.getTile(i) instanceof Land)
    		{
    			Land l = (Land)board.getTile(i);
    			if(l.getOwner() == turn)
    			{
    				ret.add(l.getName());
    			}
    		}
    		else if(board.getTile(i) instanceof Company)
    		{
    			Company c = (Company)board.getTile(i);
    			ret.add(c.getName());
    		}
    	}
    	return ret;
    }
    
    public void openGame(String path) {
    	SaveOpen.openGame(board, turn, players, path);
    }
    
    public void nextPlayer() {
		turn = Players.getInstance().nextPlayer();
		update();
	}

	public int[] throwDice() {
		dices = Dice.roll(dicesPreset);
		update();
		return dices;
	}
	
	public int[] getDicesNumbers()
	{
		return dices;
	}
	
	public Tile getTile() {
		return board.getTile(turn.getTileNumber());
	}
	
	public boolean canBuyLand() {
		if (board.getTile(turn.getTileNumber()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getTileNumber());
			return land.canBuyLand();
		}
		return false;
	}
	
	public boolean canBuildHotel() {
		if (board.getTile(turn.getTileNumber()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getTileNumber());
			return land.canBuildHotel(turn);
		}
		return false;
	}
	
	public void buyLand() throws LandException, PlayerException {
		if (board.getTile(turn.getTileNumber()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getTileNumber());
			land.buyLand(turn);
			update();
		}
	}
	
	public void buyCompany() throws PlayerException, CompanyException {
		if (board.getTile(turn.getTileNumber()) instanceof Company)
		{
			Company land = (Company)board.getTile(turn.getTileNumber());
			land.buyCompany(turn);
			update();
		}
	}
	
	public ArrayList<String> getTileInfo()
	{
		ArrayList<String> ret = new ArrayList<String>();
		if(turn != null)
		{
			Tile t = board.getTile(turn.getTileNumber());
			ret = t.print();
		}
		else
		{
			ret.add("Start");
		}
		return ret;
	}

	public boolean canBuildHouse() {
		if (board.getTile(turn.getTileNumber()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getTileNumber());
			if (land.canBuildHouse(turn)) return true;
			return false;
		}
		return false;
	}
	public void buildHouse() throws PlayerException, LandException {
		if (board.getTile(turn.getTileNumber()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getTileNumber());
			land.buildHouse();
			update();
		}
	}
	public void buildHotel() throws PlayerException, LandException {
		if (board.getTile(turn.getTileNumber()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getTileNumber());
			land.buildHotel();
			update();
		}
	}
	public void pickCard() throws PlayerException, DeckException {
		if (board.getTile(turn.getTileNumber()) instanceof LuckSetback)
		{
			LuckSetback luckSetback = (LuckSetback)board.getTile(turn.getTileNumber());
			luckSetback.pickCard(turn);
			update();
		}
	}
	
	public String getTileType()
	{
		if (turn != null) {
			Tile t = board.getTile(turn.getTileNumber());
			return t.getTileType();
		}
		return null;
	}

	public void goFoward(int dice1, int dice2) throws PlayerException {
		if (board.getTile(turn.getTileNumber()) instanceof Prision)
		{
			Prision prision = (Prision)board.getTile(turn.getTileNumber());
			prision.getOut(turn, dice1, dice2);
		}
		else
		{
			turn.goFoward(dice1 + dice2);
		}
		update();
	}

	public void landPayRent() throws LandException, PlayerException {
		if (board.getTile(turn.getTileNumber()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getTileNumber());
			land.payRent(turn);
			update();
		}
	}

	public Player companyGetOwner() {
		if (board.getTile(turn.getTileNumber()) instanceof Company)
		{
			Company company = (Company)board.getTile(turn.getTileNumber());
			return company.getOwner();
		}
		return null;
	}

	public void companyPayRent(int dice1, int dice2) throws CompanyException {
		if (board.getTile(turn.getTileNumber()) instanceof Company)
		{
			Company company = (Company)board.getTile(turn.getTileNumber());
			company.payRent(turn, dice1 + dice2);
			update();
		}
		
	}

	public void moneyExecute() {
		if (board.getTile(turn.getTileNumber()) instanceof Money)
		{
			Money money = (Money)board.getTile(turn.getTileNumber());
			money.execute(turn);
			update();
		}
	}

	public void luckSetbackPickCard() throws PlayerException, DeckException {
		if (board.getTile(turn.getTileNumber()) instanceof LuckSetback)
		{
			LuckSetback luckSetback = (LuckSetback)board.getTile(turn.getTileNumber());
			luckSetback.pickCard(turn);
			update();
		}
	}

	public void gotoPrision() {
		if (board.getTile(turn.getTileNumber()) instanceof GoToPrision)
		{
			GoToPrision goToPrision = (GoToPrision)board.getTile(turn.getTileNumber());
			goToPrision.gotoPrision(turn);
			update();
		}
	}
	public void processCard()
	{
		if (board.getTile(turn.getTileNumber()) instanceof Prision)
		{
			Prision prision = (Prision)board.getTile(turn.getTileNumber());
			prision.processCard(turn);
			update();
		}
	}
	public void saveGame(String path)
	{
		SaveOpen.saveGame(board, turn, players, path);
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void remObserver(Observer o) {
		observers.remove(o);
	}
	@Override
	public Observer getObserver(int i) {
		return observers.get(i);
	}
	void update()
	{
		for(Observer o: observers)
		{
			o.note(this);
		}
	}
	public boolean canBuyCompany() {
		if (board.getTile(turn.getTileNumber()) instanceof Company)
		{
			Company company = (Company)board.getTile(turn.getTileNumber());
			return company.canBuyCompany();
		}
		return false;
	}
	public ArrayList<String> getEndText()
	{
		return Players.getInstance().getEndText();
	}
}
