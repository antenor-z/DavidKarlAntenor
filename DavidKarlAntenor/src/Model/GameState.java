// Part of API
package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GameState {
    static GameState instance;
    GameSettings settings = GameSettings.getInstance();
    public ArrayList<Player> players = new ArrayList<Player>();
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

    public void dump() {
    	String s = "----- Game state debug start -----\n";
    	for(Player player: players) {
    		if(turn == player) {
    			s += "CURRENT PLAYER BELLOW:\n";
    		}
    		s += player.dump();
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
    		players.add(new Player(settings.getStartingBalance(), settings.getBoardSize(), 
    				color, name));
    	}
    	else
    	{
    		throw new GameException("Max numbers of players is 6");
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
		return dices;
	}
	
	public Tile getTile() {
		return board.getTile(turn.getCurrentTile());
	}
	
	public boolean canBuyLand() {
		if (board.getTile(turn.getCurrentTile()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getCurrentTile());
			return land.canBuyLand();
		}
		return false;
	}
	
	public boolean canBuildHotel() {
		if (board.getTile(turn.getCurrentTile()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getCurrentTile());
			return land.canBuildHotel(turn);
		}
		return false;
	}
	
	public void buyLand() throws LandException, PlayerException {
		if (board.getTile(turn.getCurrentTile()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getCurrentTile());
			land.buyLand(turn);
		}
	}
	
	public void buyCompany() throws PlayerException, CompanyException {
		if (board.getTile(turn.getCurrentTile()) instanceof Company)
		{
			Company land = (Company)board.getTile(turn.getCurrentTile());
			land.buyCompany(turn);
		}
	}
	
	public ArrayList<String> getCurrentTileInfo()
	{
		ArrayList<String> ret = new ArrayList<String>();
		if(turn != null)
		{
			Tile t = board.getTile(turn.getCurrentTile());
			ret = t.print();
		}
		else
		{
			ret.add("Start");
		}
		return ret;
	}

	public boolean canBuildHouse() {
		if (board.getTile(turn.getCurrentTile()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getCurrentTile());
			if (land.canBuildHouse(turn)) return true;
			return false;
		}
		return false;
	}
	public void buildHouse() throws PlayerException, LandException {
		if (board.getTile(turn.getCurrentTile()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getCurrentTile());
			land.buildHouse();
		}
	}
	
	public void pickCard() throws PlayerException, DeckException {
		if (board.getTile(turn.getCurrentTile()) instanceof LuckSetback)
		{
			LuckSetback luckSetback = (LuckSetback)board.getTile(turn.getCurrentTile());
			luckSetback.pickCard(turn);
		}
	}
	
	public String getCurrentTileType()
	{
		Tile t = board.getTile(turn.getCurrentTile());
		return t.tileType.toString();
	}

	public void goFoward(int dice1, int dice2) throws PlayerException {
		if (board.getTile(turn.getCurrentTile()) instanceof Prision)
		{
			Prision prision = (Prision)board.getTile(turn.getCurrentTile());
			prision.getOut(turn, dice1, dice2);
		}
		else
		{
			turn.goFoward(dice1 + dice2);
		}
	}

	public void landPayRent() throws LandException, PlayerException {
		if (board.getTile(turn.getCurrentTile()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getCurrentTile());
			land.payRent(turn);
		}
	}

	public Player companyGetOwner() {
		if (board.getTile(turn.getCurrentTile()) instanceof Company)
		{
			Company company = (Company)board.getTile(turn.getCurrentTile());
			return company.getOwner();
		}
		return null;
	}

	public void companyPayRent(int dice1, int dice2) throws CompanyException {
		if (board.getTile(turn.getCurrentTile()) instanceof Company)
		{
			Company company = (Company)board.getTile(turn.getCurrentTile());
			company.payRent(turn, dice1 + dice2);
		}
		
	}

	public void moneyExecute() {
		if (board.getTile(turn.getCurrentTile()) instanceof Money)
		{
			Money money = (Money)board.getTile(turn.getCurrentTile());
			money.execute(turn);	
		}
	}

	public void luckSetbackPickCard() throws PlayerException, DeckException {
		if (board.getTile(turn.getCurrentTile()) instanceof LuckSetback)
		{
			LuckSetback luckSetback = (LuckSetback)board.getTile(turn.getCurrentTile());
			luckSetback.pickCard(turn);
		}	
	}

	public void gotoPrision() {
		if (board.getTile(turn.getCurrentTile()) instanceof GoToPrision)
		{
			GoToPrision goToPrision = (GoToPrision)board.getTile(turn.getCurrentTile());
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
			playerJSON.put("@Tile", player.getCurrentTile());
			playersArrayJSON.put(playerJSON);
		}

		JSONObject currentPlayerJSON = new JSONObject();
		if(turn != null)
			currentPlayerJSON.put("CurrentPlayer", turn.getName());
		else
			currentPlayerJSON.put("CurrentPlayer", JSONObject.NULL);
		
		JSONObject tileJSON;
		JSONArray landsArrayJSON = new JSONArray();
		JSONArray companiesArrayJSON = new JSONArray();
		for(int i = 0; i < board.getLength(); i++)
		{
			if (board.getTile(i) instanceof Land)
			{
				tileJSON = new JSONObject();
				Land land = (Land)board.getTile(i);
				tileJSON.put("TileNumber", i);
				if(land.getOwner() != null)
					tileJSON.put("Owner", land.getOwner().getName());
				else
					tileJSON.put("Owner", JSONObject.NULL);
				tileJSON.put("NumberOfHouses", land.getNumberOfHouses());
				tileJSON.put("HasHotel", land.hasHotel());
				landsArrayJSON.put(tileJSON);
			}
			else if (board.getTile(i) instanceof Company)
			{
				tileJSON = new JSONObject();
				Company company = (Company)board.getTile(i);
				tileJSON.put("TileNumber", i);
				if(company.getOwner() != null)
					tileJSON.put("Owner", company.getOwner().getName());
				else
					tileJSON.put("Owner", JSONObject.NULL);
				companiesArrayJSON.put(tileJSON);
			}
		}
		JSONObject JSONFile = new JSONObject();
		JSONFile.put("Players", playersArrayJSON);
		JSONFile.put("CurrentPlayer", currentPlayerJSON);
		JSONFile.put("Lands", landsArrayJSON);
		JSONFile.put("Companies", companiesArrayJSON);
		try {
			FileWriter f = new FileWriter(path);
			f.write(JSONFile.toString(2));
			f.close();
		} catch (IOException e) {
			System.out.println("Could not save file.");
			e.printStackTrace();
		}
	}
}
