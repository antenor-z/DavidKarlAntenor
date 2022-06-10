package Model;

import java.util.ArrayList;

class Players {
	private ArrayList<Player> players = new ArrayList<Player>();
    private Player turn = null;
    static Players instance = null;
    Player getPlayer(int i)
    {
    	return players.get(i);
    }
    ArrayList<Player> getPlayersList()
    {
    	return players;
    }
    public static Players getInstance() {
        if (instance == null) {
            instance = new Players();
        }
        return instance;
    }
    private Players ()
    {

    }
    void nextPlayer() {
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
    void addPlayer(String name, PlayerColor color) throws GameException
    {
	    if(players.size() <= 6) {
			for(Player player: players) {
				if(player.getColor() == color) {
					throw new GameException("Color already used");
				}
			}
			players.add(new Player(GameSettings.getInstance().getStartingBalance(), color, name));
		}
		else
		{
			throw new GameException("Max numbers of players is 6");
		}
		//GameState.getInstance().updateObservers();
    }
    void setTurn(Player player)
    {
    	turn = player;
    }
    Player getTurn()
    {
    	if(turn == null)
    	{
    		nextPlayer();
    	}
    	return turn;
    }
}
