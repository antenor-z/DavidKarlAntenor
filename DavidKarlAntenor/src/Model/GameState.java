// Part of API
package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GameState implements Model.Observed {
    static GameState instance;
    GameSettings settings = GameSettings.getInstance();
    public ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Observer> observers = new ArrayList<Observer>();
    public Board board = new Board(players);
    public Player turn = null;
    public Float duration = 0.f;
    public int[] dices = new int[2];
    int[] dicesPreset = new int[2];
    
    private GameState() {
    	dicesPreset[0] = dicesPreset[1] = -1;
    }
    
    public void setDice1Preset(int value) 
    {
    	this.dicesPreset[0] = value;
    }
    
    public void setDice2Preset(int value) 
    {
    	this.dicesPreset[1] = value;
    }

    public ArrayList<ArrayList<Object>> getFormatedLandsCompany()
    {
    	return board.getFormatedLandsCompany();
    }
    
    public void dump() {
    	System.out.println(board.getFormatedLandsCompany());
    	String s = "----- Game state debug start -----\n";
    	for(Player player: players) {
    		if(turn == player) {
    			s += "CURRENT PLAYER BELLOW:\n";
    		}
    		s += player.toString();
    	}
    	s += "----- Game state debug end -----\n";
    	System.out.println(s);
    }
    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }
    
    public void addPlayer(String name, PlayerColor color) throws GameException {
    	if(players.size() <= 6) {
    		for(Player player: players) {
    			if(player.getColor() == color) {
    				throw new GameException("Color already used");
    			}
    		}
    		players.add(new Player(settings.getStartingBalance(), color, name));
    	}
    	else
    	{
    		throw new GameException("Max numbers of players is 6");
    	}
    }
    
    public void openGame(String path) {
    	try {
			String content = Files.readString(Path.of(path));
			
			JSONObject obj = new JSONObject(content);
            JSONArray jsonPlayers = obj.getJSONArray("Players");
            String currentPlayerName;
            if(!obj.isNull("CurrentPlayer"))
            	currentPlayerName = obj.getString("CurrentPlayer");
            else
            	currentPlayerName = null;
            
            for (Object playerO: jsonPlayers) {
            	JSONObject player = (JSONObject)playerO;
            	String name = player.getString("Name");
            	String colorS = player.getString("Color");
            	PlayerColor color = PlayerColor.valueOf(colorS);
            	int cash = player.getInt("Cash");
            	int tile = player.getInt("@Tile");
            	Player newPlayer = new Player(cash, color, name);
            	newPlayer.goToTile(tile);
            	players.add(newPlayer);
            	if(currentPlayerName != null)
            	{
	            	if(currentPlayerName.equals(name))
	            	{
	            		turn = newPlayer;
	            	}
            	}
            }
            board = new Board(obj);
    	} catch(Exception e) {
			System.out.println("Failed to read Board.json");
			e.printStackTrace();
		}	
    }
    
    public void nextPlayer() {
    	if(turn == null) {
    		turn = players.get(0);
    	} else {
    		int turnN;
    		for(turnN = 0; turnN < players.size(); turnN++) {
    			if(players.get(turnN) == turn) {
    				break;
    			}
    		}
    		turnN = (turnN + 1) % players.size();
    		turn = players.get(turnN);
    	}
    }

	public int[] throwDice() {
		dices = Dice.roll(dicesPreset);
		update();
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
		}
	}
	
	public void buyCompany() throws PlayerException, CompanyException {
		if (board.getTile(turn.getTileNumber()) instanceof Company)
		{
			Company land = (Company)board.getTile(turn.getTileNumber());
			land.buyCompany(turn);
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
		}
	}
	
	public void pickCard() throws PlayerException, DeckException {
		if (board.getTile(turn.getTileNumber()) instanceof LuckSetback)
		{
			LuckSetback luckSetback = (LuckSetback)board.getTile(turn.getTileNumber());
			luckSetback.pickCard(turn);
		}
	}
	
	public String getTileType()
	{
		Tile t = board.getTile(turn.getTileNumber());
		return t.tileType.toString();
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
	}

	public void landPayRent() throws LandException, PlayerException {
		if (board.getTile(turn.getTileNumber()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getTileNumber());
			land.payRent(turn);
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
		}
		
	}

	public void moneyExecute() {
		if (board.getTile(turn.getTileNumber()) instanceof Money)
		{
			Money money = (Money)board.getTile(turn.getTileNumber());
			money.execute(turn);	
		}
	}

	public void luckSetbackPickCard() throws PlayerException, DeckException {
		if (board.getTile(turn.getTileNumber()) instanceof LuckSetback)
		{
			LuckSetback luckSetback = (LuckSetback)board.getTile(turn.getTileNumber());
			luckSetback.pickCard(turn);
		}	
	}

	public void gotoPrision() {
		if (board.getTile(turn.getTileNumber()) instanceof GoToPrision)
		{
			GoToPrision goToPrision = (GoToPrision)board.getTile(turn.getTileNumber());
			goToPrision.gotoPrision(turn);
		}
	}
	public void saveGame(String path)
	{
		JSONObject playerJSON;
		JSONArray playersArrayJSON = new JSONArray();
		
		for(Player player: players)
		{
			playerJSON = new JSONObject();
			playerJSON.put("Name", player.getName());
			playerJSON.put("Color", player.getColor());
			playerJSON.put("Cash", player.getCash());
			playerJSON.put("@Tile", player.getTileNumber());
			playersArrayJSON.put(playerJSON);
		}
		
		JSONObject tileJSON;
		JSONObject landsObjectJSON = new JSONObject();
		JSONObject companiesObjectJSON = new JSONObject();
		for(int i = 0; i < board.getLength(); i++)
		{
			if (board.getTile(i) instanceof Land)
			{
				tileJSON = new JSONObject();
				Land land = (Land)board.getTile(i);
				if(land.getOwner() != null)
					tileJSON.put("Owner", land.getOwner().getName());
				else
					tileJSON.put("Owner", JSONObject.NULL);
				tileJSON.put("NumberOfHouses", land.getNumberOfHouses());
				tileJSON.put("HasHotel", land.hasHotel());
				landsObjectJSON.put(String.valueOf(i), tileJSON);
			}
			else if (board.getTile(i) instanceof Company)
			{
				tileJSON = new JSONObject();
				Company company = (Company)board.getTile(i);
				if(company.getOwner() != null)
					tileJSON.put("Owner", company.getOwner().getName());
				else
					tileJSON.put("Owner", JSONObject.NULL);
				companiesObjectJSON.put(String.valueOf(i), tileJSON);
			}
		}
		JSONObject JSONFile = new JSONObject();
		JSONFile.put("Players", playersArrayJSON);
		if(turn != null)
			JSONFile.put("CurrentPlayer", turn.getName());
		else
			JSONFile.put("CurrentPlayer", JSONObject.NULL);
		JSONFile.put("Lands", landsObjectJSON);
		JSONFile.put("Companies", companiesObjectJSON);
		try {
			FileWriter f = new FileWriter(path);
			f.write(JSONFile.toString(2));
			f.close();
		} catch (IOException e) {
			System.out.println("Could not save file.");
			e.printStackTrace();
		}
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

}
