package Model;

import Model.Player.Player;
import Model.gameboard.Board;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    static GameState instance;

    public List<Player> players = new ArrayList<Player>();
    public Board board = new Board();
    public Player turn = null;
    public Float duration = 0.f;

    private GameState() {
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
    		int balance = GameSettings.getInstance().getStartingBalance();
    		players.add(new Player(balance, board, color));
    	}
    	throw new GameException("Max numbers of players is 6");
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
    	}
    }
}
