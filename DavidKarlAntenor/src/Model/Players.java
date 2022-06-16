package Model;

import java.util.ArrayList;

class Players {
	private ArrayList<Player> players = new ArrayList<Player>();
    private Player turn = null;
    GameSettings settings = GameSettings.getInstance();
    static Players instance = null;
    static Players getInstance()
    {
    	if(instance == null)
    		instance = new Players();
    	return instance;
    }
    private Players()
    {
    	
    }
    Player get(int n)
    {
    	return players.get(n);
    }
    ArrayList<Player> getArray()
    {
    	return players;
    }
    public void addPlayer(String name, PlayerColor color) throws GameException {
    	if(players.size() <= 6) {
    		for(Player player: players) {
    			if(player.getColor() == color) {
    				throw new GameException("Color already used");
    			}
    		}
    		players.add(new Player(4000, color, name));
    	}
    	else
    		throw new GameException("Max numbers of players is 6");
    }
    public Player nextPlayer() {
		System.out.println("nextPlayer");
    	if(turn == null) {
    		turn = players.get(0);
		} else {
    		int turnN;
    		for(turnN = 0; turnN < players.size(); turnN++) {
    			if(players.get(turnN) == turn) {
    				break;
    			}
    		}
    		do
    		{
    			turnN = (turnN + 1) % players.size();
    			turn = players.get(turnN);
    		}
    		while(turn.isBankrupt());
    	}
    	return turn;
	}
}
