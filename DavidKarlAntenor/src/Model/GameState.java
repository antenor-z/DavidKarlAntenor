package Model;

import Model.Player.Player;
import Model.Player.PlayerException;
import Model.gameboard.Board;
import Model.gameboard.Land;
import Model.gameboard.LandException;
import Model.gameboard.Tile;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    static GameState instance;
    GameSettings settings = GameSettings.getInstance();
    public ArrayList<Player> players = new ArrayList<Player>();
    public Board board = new Board(players);
    public Player turn = null;
    public Float duration = 0.f;
    public int[] dices = new int[2];
    
    private GameState() {

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
		dices = Dice.roll();
		return dices;
	}
	
	public Tile getTile() {
		return board.getTile(turn.getCurrentTile());
	}
	
	boolean canBuyLand() {
		if (board.getTile(turn.getCurrentTile()) instanceof Land)
		{
			Land land = (Land)board.getTile(turn.getCurrentTile());
			if (land.getOwner() == null) return true;
			return false;
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
}
