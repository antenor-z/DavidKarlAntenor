package Model;

import Model.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    static GameState instance;

    public List<Player> players = new ArrayList<Player>();
    public Player turn = null;
    public Float duration = 0.f;

    public GameState() {
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }
}
