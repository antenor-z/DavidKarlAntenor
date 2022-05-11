package Model;

public class GameSettings {
    static GameSettings instance;

    private Integer maxPlayer = 6;
    private Integer minPlayer = 2;
    private Float maxDuration = -1.f; // set to -1 if there is no max duration
    private Integer startingBalance = 4000;
    private Integer nbDices = 2;

    public GameSettings() {
    }

    public static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        return instance;
    }

    public Integer getMaxPlayer() {
        return maxPlayer;
    }

    public Integer getMinPlayer() {
        return minPlayer;
    }

    public Float getMaxDuration() {
        return maxDuration;
    }

    public Integer getStartingBalance() {
        return startingBalance;
    }

    public Integer getNbDices() {
        return nbDices;
    }
    
    
}
